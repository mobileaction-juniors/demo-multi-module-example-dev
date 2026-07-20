package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserRequestHandlerService implements IUserRequestHandlerService
{
    private final AmqpTemplate userResultQueueTemplate;
    private final ICrawlerClient crawlerClient;

    public UserRequestHandlerService(AmqpTemplate userResultQueueTemplate, ICrawlerClient crawlerClient)
    {
        this.userResultQueueTemplate = userResultQueueTemplate;
        this.crawlerClient = crawlerClient;
    }

    @Override
    public void executeMessage(UserQueueRequestDto requestDto)
    {
        UserDto userDto = crawlerClient.fetchUser(requestDto.getUserId());

        userResultQueueTemplate.convertAndSend(userDto);
    }
}
