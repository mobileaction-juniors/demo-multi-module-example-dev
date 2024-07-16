package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.common.dto.UserQueueRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService
{
    @Autowired
    @Qualifier("userRequestQueueTemplate")
    private final AmqpTemplate userRequestQueueTemplate;

    @Override
    public void sendRequestForDistinctUsers(List<Long> userIds)
    {
        userIds.stream()
                .map(UserQueueRequestDto::new)
                .forEach(userRequestQueueTemplate::convertAndSend);
    }
}
