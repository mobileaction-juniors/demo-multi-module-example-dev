package co.mobileaction.example.web.service;
import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.common.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService
{
    private final AmqpTemplate userRequestQueueTemplate;

    @Override
    public void sendUserRequestForAllItems(List<Long> userIds)
    {
        userIds.stream()
                .map(userId -> new UserRequestDto(userId))
                .forEach(userRequestDto -> userRequestQueueTemplate.convertAndSend(userRequestDto));
    }
}
