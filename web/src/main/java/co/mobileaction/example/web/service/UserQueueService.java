package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService
{
    private final AmqpTemplate userRequestQueueTemplate;

    private final IPostService postService;

    @Override
    public void sendUserRequestForAllItems()
    {
        // Distinct IDs prevent duplicate crawl requests for users with multiple posts.
        postService.findDistinctUserIds().stream()
                .map(UserQueueRequestDto::new)
                .forEach(userRequestQueueTemplate::convertAndSend);
    }
}
