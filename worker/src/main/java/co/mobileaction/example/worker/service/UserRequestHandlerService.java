package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.QueueUserDto;
import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRequestHandlerService implements IUserRequestHandlerService{
    private final AmqpTemplate resultUserQueueTemplate;

    private final ICrawlerClient crawlerClient;

    public void executeUser(QueueUserDto request)
    {
        UserDto userDto = crawlerClient.fetchUser(request.getUserId());

        resultUserQueueTemplate.convertAndSend(userDto);
    }

}
