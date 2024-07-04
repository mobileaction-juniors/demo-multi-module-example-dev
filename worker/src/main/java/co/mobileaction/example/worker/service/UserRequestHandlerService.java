package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.common.dto.UserQueueDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * @author sa
 * @date 17.05.2021
 * @time 16:55
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserRequestHandlerService implements IUserRequestHandlerService
{
    private final AmqpTemplate resultUserQueueTemplate;
    private final ICrawlerClient crawlerClient;

    @Override
    public void executeMessage(UserQueueDto request)
    {
        UserDto user = crawlerClient.fetchUser(request.getId());
        resultUserQueueTemplate.convertAndSend(user);
    }
}
