package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.worker.service.IUserRequestHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author berkturk
 * @date 22.06.2026
 * @time 17:33
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserRequestQueueHandler
{
    private final AmqpTemplate requestProblemQueueTemplate;

    private final IUserRequestHandlerService userRequestHandlerService;

    @RabbitListener(queues = "${messaging.queue.user.request}", containerFactory = "requestQueueListener")
    public void handleMessage(UserQueueRequestDto request)
    {
        try
        {
            userRequestHandlerService.executeMessage(request);
        }
        catch (Exception e)
        {
            log.error("Could not handle request for userId: {}", request.getUserId(), e);

            requestProblemQueueTemplate.convertAndSend(request);
        }
    }
}
