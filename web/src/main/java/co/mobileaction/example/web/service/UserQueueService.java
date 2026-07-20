package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.web.repository.IPostRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueueService implements IUserQueueService
{
    private final IPostRepository postRepository;
    private final AmqpTemplate userRequestQueueTemplate;

    public UserQueueService(IPostRepository postRepository, AmqpTemplate userRequestQueueTemplate)
    {
        this.postRepository = postRepository;
        this.userRequestQueueTemplate = userRequestQueueTemplate;
    }

    @Override
    public void sendPostRequestForAllUsers()
    {
        List<Long> distinctUsers = postRepository.findDistinctUsers();
        distinctUsers.stream().map(UserQueueRequestDto::new)
                .forEach(userRequestQueueTemplate::convertAndSend);
    }
}
