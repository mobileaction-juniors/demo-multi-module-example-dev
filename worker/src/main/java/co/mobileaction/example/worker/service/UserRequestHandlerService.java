package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRequestHandlerService implements IUserRequestHandlerService
{
    private final AmqpTemplate userResultQueueTemplate;

    private final AmqpTemplate userRequestProblemQueueTemplate;

    private final ICrawlerClient crawlerClient;

    @Override
    public void executeMessage(UserQueueRequestDto request)
    {
        try
        {
            UserDto user = crawlerClient.fetchUser(request.userId());

            userResultQueueTemplate.convertAndSend(user);
        }
        catch (Exception e)
        {
            log.error("Could not handle request for userId: {}", request.userId(), e);

            userRequestProblemQueueTemplate.convertAndSend(request);
        }
    }
}
