package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.worker.client.IUserCrawlerClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRequestHandlerService implements IUserRequestHandlerService {
    private final AmqpTemplate resultQueueTemplate;
    private final IUserCrawlerClient userCrawlerClient;

    @Value("${messaging.queue.user-result}")
    private String userResultQueueName;

    @Override
    public void executeMessage(Long userId) {
        UserDto user = userCrawlerClient.fetchUser(userId);
        resultQueueTemplate.convertAndSend(userResultQueueName, user);
    }
}