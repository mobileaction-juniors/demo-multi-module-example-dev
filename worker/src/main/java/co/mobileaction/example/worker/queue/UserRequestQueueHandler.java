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

    private final AmqpTemplate requestProblemQueueUserTemplate;

    private final IUserRequestHandlerService requestHandlerService;

    @RabbitListener(queues = "${messaging.queue.request.user}", containerFactory = "requestQueueListener")
    public void handleMessage(UserQueueRequestDto request)
    {
        try
        {
            requestHandlerService.executeMessage(request);
        }
        catch (Exception e)
        {
            log.error("Could not handle request for postId: {}", request.getUser_id(), e);
            //log.error("{}", request);
            requestProblemQueueUserTemplate.convertAndSend(request);
        }
    }


}
