package co.mobileaction.example.web.service.user;

import co.mobileaction.example.common.dto.QueueUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService
{
    private final AmqpTemplate userRequestQueueTemplate;

    @Override
    public void sendUserRequestForAllItems(List<Long> userIds)
    {
        userIds.stream().map(QueueUserRequestDto::new)
            .forEach(userRequestQueueTemplate::convertAndSend);
    }
}
