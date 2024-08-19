package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.QueueRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.LongStream;

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
