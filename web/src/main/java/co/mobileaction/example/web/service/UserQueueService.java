package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.web.repository.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Yunus Gunay
 */
@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService
{
    private final IPostRepository postRepository;
    private final AmqpTemplate userRequestQueueTemplate;

    @Override
    public void sendUserRequestForAllPostUsers()
    {
        postRepository.findDistinctUserIds()
                .stream()
                .map(UserQueueRequestDto::new)
                .forEach(userRequestQueueTemplate::convertAndSend);
    }
}
