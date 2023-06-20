package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.QueueRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.LongStream;

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

    @Override
    public void sendPostRequestForAllItems()
    {
        LongStream.rangeClosed(1, 100)
                .mapToObj(QueueRequestDto::new)
                .forEach(requestQueueTemplate::convertAndSend);
    }
}
