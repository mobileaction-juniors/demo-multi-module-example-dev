package co.mobileaction.example.web.queue;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.web.service.IPostResultHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:38
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PostResultQueueHandler
{
    private final AmqpTemplate resultProblemQueueTemplate;

    private final IPostResultHandlerService resultHandlerService;

    @RabbitListener(queues = "${messaging.queue.result}", containerFactory = "resultQueueListener")
    public void handleMessage(PostDto result)
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
