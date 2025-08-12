package co.mobileaction.example.web.service.User;

import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.web.service.Post.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService
{
    private final AmqpTemplate requestUserQueueTemplate;
    private final IPostService postService;

    @Override
    public void sendUserRequestForAllItems()
    {
        List<Long> allUsers = postService.findDistinctUsers();

        allUsers.stream()
                .map(QueueRequestDto::new)
                .forEach(requestUserQueueTemplate::convertAndSend);
    }
}
