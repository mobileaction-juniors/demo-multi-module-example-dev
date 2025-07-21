package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.QueueRequestDto;
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

    @RabbitListener(queues = "${messaging.queue.user-request}", containerFactory = "requestQueueListener")
    public void handleMessage(QueueRequestDto request) {
        try {
            Long userId = request.getUserId();
            if (userId == null) {
                log.error("Received user crawl request with null userId: {}", request);
                requestProblemQueueTemplate.convertAndSend(request);
                return;
            }
            requestHandlerService.executeMessage(userId);
        } catch (Exception e) {
            log.error("Could not handle request for userId: {}", request.getUserId(), e);
            requestProblemQueueTemplate.convertAndSend(request);
        }
    }
}