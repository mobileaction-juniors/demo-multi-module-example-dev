package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.worker.service.IUserRequestHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Doga Elif Konuk
 * @date 17.07.2024
 * @time 15:15
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserRequestQueueHandler
{

    private final AmqpTemplate userRequestProblemQueueTemplate;

    private final IUserRequestHandlerService userRequestHandlerService;

    @RabbitListener(queues = "ma-example-user-request-queue", containerFactory = "requestQueueListener")
    public void handleMessage(UserQueueRequestDto userRequest)
    {
        try
        {
            userRequestHandlerService.executeMessage(userRequest);
        }
        catch (Exception e)
        {
            log.error("Could not handle request for userId: {}", userRequest.getUserId(), e);

            userRequestProblemQueueTemplate.convertAndSend(userRequest);
        }
    }
}
