package co.mobileaction.example.worker.queue;

import co.mobileaction.example.worker.service.IPostRequestHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author sa
 * @date 17.05.2021
 * @time 16:16
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PostRequestQueueHandler
{
    private final AmqpTemplate requestProblemQueueTemplate;

    private final IPostRequestHandlerService requestHandlerService;

    @RabbitListener(queues = "${messaging.queue.request}", containerFactory = "requestQueueListener")
    public void handleMessage(QueueRequestDto request)
    {
        try
        {
            requestHandlerService.executeMessage(request);
        }
        catch (Exception e)
        {
            log.error("Could not handle request for postId: {}", request.getPostId(), e);

            requestProblemQueueTemplate.convertAndSend(request);
        }
    }
}
