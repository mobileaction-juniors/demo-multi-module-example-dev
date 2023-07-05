package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.QueueRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import co.mobileaction.example.common.dto.UserRequestDto;

import java.util.List;
import java.util.stream.LongStream;

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
        List<Long> userIds = postService.findAllDistinctUserIds();

        userIds.stream().mapToLong(Long::longValue)
                .mapToObj(UserRequestDto::new)
                .forEach(userRequestQueueTemplate::convertAndSend);
    }
}
