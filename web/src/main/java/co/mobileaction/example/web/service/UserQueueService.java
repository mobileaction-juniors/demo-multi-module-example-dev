package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.QueueUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService
{
    private final AmqpTemplate userRequestQueueTemplate;
    private final IPostService postService;

    @Override
    public void sendUserRequestsForAllItems()
    {
        postService.findAllDistinctUsers()
                .stream()
                .map(QueueUserRequestDto::new)
                .forEach(userRequestQueueTemplate::convertAndSend);
    }
}
