package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.common.dto.UserQueueRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.LongStream;

/**
 * @author berkturk
 * @date 22.06.2026
 * @time 17:08
 */
@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService
{
    private final AmqpTemplate requestQueueTemplate;

    @Override
    public void sendUserRequestForDistinctItems()
    {
        LongStream.rangeClosed(1, 100)
                .mapToObj(UserQueueRequestDto::new)
                .forEach(requestQueueTemplate::convertAndSend);
    }
}
