package co.mobileaction.example.worker.service.User;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
import co.mobileaction.example.worker.service.Post.IPostRequestHandlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * @author sa
 * @date 17.05.2021
 * @time 16:55
 */
@Service
@RequiredArgsConstructor
public class UserRequestHandlerService implements IUserRequestHandlerService
{
    private final AmqpTemplate resultUserQueueTemplate;

    private final ICrawlerClient crawlerClient;

    @Override
    public void executeMessage(QueueRequestDto request)
    {
        UserDto user = crawlerClient.fetchUser(request.getPostId());

        resultUserQueueTemplate.convertAndSend(user);
    }
}
