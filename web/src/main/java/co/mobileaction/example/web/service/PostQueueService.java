package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.common.dto.QueueRequestUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import java.util.stream.LongStream;
import java.util.List;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:57
 */
@Service
@RequiredArgsConstructor
public class PostQueueService implements IPostQueueService
{
    private final AmqpTemplate requestQueueTemplate;

    private final AmqpTemplate userRequestQueueTemplate;

    @Override
    public void sendPostRequestForAllItems()
    {
        LongStream.rangeClosed(1, 100)
                .mapToObj(QueueRequestDto::new)
                .forEach(requestQueueTemplate::convertAndSend);
    }

    @Override
    public boolean sendAllUniqueUserIds(List<Long> userIds)
    {
        try
        {
            userIds.forEach(userId -> userRequestQueueTemplate.convertAndSend(new QueueRequestUserDto(userId)));
        }
        catch (AmqpException e)
        {
            return false;
        }
        return true;
    }
}
