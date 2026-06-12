package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author serkankorkut
 * @date 12.06.2026
 * @time 14:27
 */
@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService
{
    @Qualifier("userRequestQueueTemplate")
    private final AmqpTemplate userRequestQueueTemplate;

    private final IPostService postService;

    @Override
    public void sendUserRequestForDistinctPostUsers()
    {
        postService.findDistinctUserIds().stream()
                .map(UserQueueRequestDto::new)
                .forEach(userRequestQueueTemplate::convertAndSend);
    }
}
