package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.UserCrawlRequestDto;
import co.mobileaction.example.worker.service.IUserRequestHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRequestQueueHandler {
    private final IUserRequestHandlerService requestHandlerService;

    @RabbitListener(queues = "${messaging.queue.userRequest}", containerFactory = "requestQueueListener")
    public void handleMessage(UserCrawlRequestDto request) {
        try {
            requestHandlerService.executeMessage(request);
        } catch (Exception e) {
            log.error("Could not handle request for userId: {}", request.getId(), e);
        }
    }
}