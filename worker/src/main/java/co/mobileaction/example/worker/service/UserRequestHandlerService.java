package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.common.dto.UserRequestDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
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
    private final AmqpTemplate userResultQueueTemplate;

    private final ICrawlerClient crawlerClient;

    @Override
    public void executeMessage(UserRequestDto request)
    {
        try {
            UserDto user = crawlerClient.fetchUser(request.getUserId());

            userResultQueueTemplate.convertAndSend(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
