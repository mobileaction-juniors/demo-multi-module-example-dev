package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.UserRequestDto;
import co.mobileaction.example.worker.service.IUserRequestHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 /**
 * @author elif
 * @date 05.07.2023
 * @time 17.10
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserRequestQueueHandler
{
    private final AmqpTemplate userRequestProblemQueueTemplate;

    private final IUserRequestHandlerService userRequestHandlerService;

    @RabbitListener(queues = "${messaging.queue.user.request}", containerFactory = "requestQueueListener")
    public void handleMessage(UserRequestDto request)
    {
        try
        {
            userRequestHandlerService.executeMessage(request);
        }
        catch (Exception e)
        {
            log.error("Could not handle request for userId: {}", request.getUserId(), e);

            userRequestProblemQueueTemplate.convertAndSend(request);
        }
    }
}
