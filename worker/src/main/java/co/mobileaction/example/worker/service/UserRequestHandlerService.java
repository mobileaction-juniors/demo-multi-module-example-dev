package co.mobileaction.example.worker.service;

import co.mobileaction.example.worker.client.ICrawlerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRequestHandlerService implements IUserRequestHandlerService
{
    private final AmqpTemplate userResultQueueTemplate;

    private final ICrawlerClient crawlerClient;

    @Override
    public void executeMessage(UserRequestDto request)
    {
        UserDto user = crawlerClient.fetchUser(request.getUserId());
        userResultQueueTemplate.convertAndSend(user);
    }
}
