package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserRequestDto;
import co.mobileaction.example.web.repository.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService
{
    private final AmqpTemplate userRequestQueueTemplate;
    private final IPostRepository postRepository;

    @Override
    public void sendUserRequestForAllDistinctIds()
    {
        List<Long> distinctUserIds = postRepository.findDistinctUserIds();
        distinctUserIds.stream()
                .map(UserRequestDto::new)
                .forEach(userRequestQueueTemplate::convertAndSend);
    }
}
