package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.web.service.IPostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService {
    private static final Logger logger = LoggerFactory.getLogger(UserQueueService.class);
    private final IPostService postService;
    private final AmqpTemplate requestQueueTemplate;

    @Value("${messaging.queue.request}")
    private String requestQueueName;

    @Value("${messaging.queue.user-request}")
    private String userRequestQueueName;

    @Override
    public void sendUserCrawlRequestForAllUserIds() {
        List<Long> userIds = postService.findDistinctUserIds();
        for (Long userId : userIds) {
            requestQueueTemplate.convertAndSend(userRequestQueueName, QueueRequestDto.forUser(userId));
        }
    }
}