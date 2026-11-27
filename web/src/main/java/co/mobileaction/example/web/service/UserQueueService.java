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
    public void sendUserRequestForAllUsers()
    {
        postService.findDistinctUserIds().stream()
                .map(UserQueueRequestDto::new)
                .forEach(userRequestQueueTemplate::convertAndSend);
    }
}
