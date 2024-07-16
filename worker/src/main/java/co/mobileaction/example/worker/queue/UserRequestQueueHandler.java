package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.worker.service.IUserRequestHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRequestQueueHandler {
    private final AmqpTemplate userRequestProblemQueueTemplate;

    private final IUserRequestHandlerService userRequestHandlerService;

    @RabbitListener(queues = "${user.messaging.queue.request.problem}", containerFactory = "requestQueueListener")
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
