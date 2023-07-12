package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.common.dto.QueueRequestUserDto;
import co.mobileaction.example.worker.service.IPostRequestHandlerService;
import co.mobileaction.example.worker.service.IUserRequestHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRequestQueueHandler {

    private final AmqpTemplate userRequestProblemQueueTemplate;

    private final IUserRequestHandlerService userRequestHandlerService;

    @RabbitListener(queues = "${messaging.user.queue.request}" , containerFactory = "requestQueueListener")
    public void handleMessage(QueueRequestUserDto request)
    {
        try
        {
            userRequestHandlerService.executeMessage(request);
        }
        catch (Exception e){
            log.error("Could not handle request for userId: {}", request.getUserId(), e);
            userRequestProblemQueueTemplate.convertAndSend(request);
        }
    }

}
