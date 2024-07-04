package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Mehmet Akif Sahin
 * @date 03.07.2024
 * @time 10:45
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserRequestHandlerService implements IUserRequestHandlerService
{
    private final AmqpTemplate userResultQueueTemplate;

    private final ICrawlerClient crawlerClient;

    @Override
    public void executeMessage(UserQueueRequestDto request)
    {
        UserDto user = crawlerClient.fetchUser(request.getUserId());


        userResultQueueTemplate.convertAndSend(user);
    }
}
