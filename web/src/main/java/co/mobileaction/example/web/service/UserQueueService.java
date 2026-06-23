package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

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

    private final PostService postService;

    @Override
    public void sendUserRequestForDistinctItems()
    {
        postService.findDistinctUserIds().stream()
                .map(UserQueueRequestDto::new)
                .forEach(requestQueueTemplate::convertAndSend);
    }
}
