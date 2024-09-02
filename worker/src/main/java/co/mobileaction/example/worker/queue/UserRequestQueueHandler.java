package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.QueueRequestUserDto;
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

    @RabbitListener(queues = "${messaging.queue.requestUser}", containerFactory = "requestQueueListener")
    public void handleMessage(QueueRequestUserDto request)
    {
        try
        {
            requestHandlerService.executeMessage(request);
        }
        catch (Exception e)
        {
            log.error("Could not handle request for userId: {}", request.getUserId(), e);

            requestProblemQueueTemplate.convertAndSend(request);
        }
    }
}
