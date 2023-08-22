package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.QueueUserDto;
import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.worker.service.IPostRequestHandlerService;
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

    private final AmqpTemplate requestProblemQueueTemplate;

    private final IUserRequestHandlerService requestHandlerService;

    @RabbitListener(queues = "${messaging.queue.request.user}", containerFactory = "requestQueueListener")
    public void handleUser(QueueUserDto request)
    {
        try
        {
            requestHandlerService.executeUser(request);
        }
        catch (Exception e)
        {
            log.error("Could not handle request for postId: {}", request.getUserId(), e);

            requestProblemQueueTemplate.convertAndSend(request);
        }
    }


}
