package co.mobileaction.example.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService
{
    private final AmqpTemplate requestQueueTemplate;
    private final PostService postService;
    @Override
    public void sendUserRequestsForAllItems(){


    }

}
