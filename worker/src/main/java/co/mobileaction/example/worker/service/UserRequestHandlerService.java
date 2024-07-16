package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.worker.client.IUserCrawlerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRequestHandlerService implements IUserRequestHandlerService {

    @Autowired
    @Qualifier("userResultQueueTemplate")
    private final AmqpTemplate userResultQueueTemplate;

    private final IUserCrawlerClient userCrawlerClient;

    @Override
    public void executeMessage(UserQueueRequestDto userRequest)
    {
        UserDto user = userCrawlerClient.fetchUser(userRequest.getUserId());

        userResultQueueTemplate.convertAndSend(user);
    }
}
