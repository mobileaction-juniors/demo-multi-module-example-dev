package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.worker.service.IUserRequestHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Mehmet Akif Sahin
 * @date 03.07.2024
 * @time 10:24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserRequestQueueHandler
{
    private final AmqpTemplate userRequestProblemQueueTemplate;
    private final IUserRequestHandlerService userRequestHandlerService;

    @RabbitListener(queues="${messaging.queue.user.request}", containerFactory = "requestQueueListener")
    public void handleMessage (UserQueueRequestDto request)
    {
        try
        {
            userRequestHandlerService.executeMessage(request);
        }
        catch (Exception e)
        {
            log.error("Could not handle user request for userId: {}", request.getUserId(), e);

            userRequestProblemQueueTemplate.convertAndSend(request);
        }
    }

}
