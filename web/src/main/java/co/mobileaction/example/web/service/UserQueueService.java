package co.mobileaction.example.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author elif
 * @date 05.07.2023
 * @time 17.20
 */
@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService
{
    private final AmqpTemplate userRequestQueueTemplate;
    private final IPostService postService;

    @Override
    public void sendUserRequestForAllPosts()
    {
        // find all distinct userIds from post table
        Set<Long> userIds = postService.findAllDistinctUserIds();

        userIds.stream()
                .forEach(userRequestQueueTemplate::convertAndSend);
    }
}
