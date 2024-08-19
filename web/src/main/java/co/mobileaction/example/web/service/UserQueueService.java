package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService {
    private final AmqpTemplate userRequestQueueTemplate;

    @Override
    public void sendUserRequests(Set<Long> userIds) {
        userIds.stream()
                .map(UserQueueRequestDto::new)
                .forEach(userRequestQueueTemplate::convertAndSend);
    }
}