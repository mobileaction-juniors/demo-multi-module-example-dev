package co.mobileaction.example.web.queue;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.service.UserResultHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserResultQueueHandler
{
    private final AmqpTemplate resultProblemQueueTemplate;
    private final UserResultHandlerService resultHandlerService;

    public UserResultQueueHandler(AmqpTemplate resultProblemQueueTemplate, UserResultHandlerService resultHandlerService)
    {
        this.resultProblemQueueTemplate = resultProblemQueueTemplate;
        this.resultHandlerService = resultHandlerService;
    }

    @RabbitListener(queues = "${messaging.queue.user.result}", containerFactory = "resultQueueListener")
    public void handleMessage(UserDto result)
    {
        try
        {
            resultHandlerService.executeMessage(result);
        }
        catch (Exception e)
        {
            log.error("Could not handle result for postId: {}", result.getId(), e);

            resultProblemQueueTemplate.convertAndSend(result);
        }
    }
}
