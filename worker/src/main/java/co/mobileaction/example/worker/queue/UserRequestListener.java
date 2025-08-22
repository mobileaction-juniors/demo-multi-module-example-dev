package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.QueueNames;
import co.mobileaction.example.common.dto.UserCrawlRequestDto;
import co.mobileaction.example.common.dto.UserCrawlResultDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.Map;

@Component
public class UserRequestListener
{
    private final WebClient userClient;
    private final AmqpTemplate amqpTemplate;

    public UserRequestListener(WebClient userClient,
                               @Qualifier("rabbitTemplate") AmqpTemplate amqpTemplate)
    {
        this.userClient = userClient;
        this.amqpTemplate = amqpTemplate;
    }

    @RabbitListener(queues = QueueNames.CRAWL_USER_REQUEST)
    public void onRequest(UserCrawlRequestDto req) 
    {
        Map<String, Object> raw = userClient.get()
                .uri("/users/{id}", req.getUserId())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

        if (raw == null || raw.isEmpty())
        {
            return;
        }

        Long id = raw.get("id") != null ? ((Number) raw.get("id")).longValue() : req.getUserId();
        String name = (String) raw.getOrDefault("name", "");
        String username = (String) raw.getOrDefault("username", "");

        UserCrawlResultDto result = new UserCrawlResultDto(id, name, username);

        amqpTemplate.convertAndSend(QueueNames.CRAWL_USER_RESULT, result);
    }
}
