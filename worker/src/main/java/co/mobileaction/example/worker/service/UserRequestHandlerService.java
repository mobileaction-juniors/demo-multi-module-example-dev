package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.worker.client.IUserCrawlerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Doga Elif Konuk
 * @date 17.07.2024
 * @time 15:15
 */
@Service
@RequiredArgsConstructor
public class UserRequestHandlerService implements IUserRequestHandlerService {

    private final AmqpTemplate userResultQueueTemplate;

    private final IUserCrawlerClient userCrawlerClient;

    @Override
    public void executeMessage(UserQueueRequestDto userRequest)
    {
        UserDto user = userCrawlerClient.fetchUser(userRequest.getUserId());

        userResultQueueTemplate.convertAndSend(user);
    }
}
