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

    @RabbitListener(queues = "ma-example-user-queue", containerFactory = "requestQueueListener")
    public void handleMessage(UserQueueRequestDto request) {
        try {
            userRequestHandlerService.executeMessage(request);
        } catch (Exception e) {
            log.error("Could not handle request for postId: {}", request.getId(), e);

            userRequestProblemQueueTemplate.convertAndSend(request);
        }
    }
}