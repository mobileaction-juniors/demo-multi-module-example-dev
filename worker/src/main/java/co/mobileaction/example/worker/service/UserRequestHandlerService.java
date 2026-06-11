package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRequestHandlerService implements IUserRequestHandlerService {

    private final AmqpTemplate userResultQueueTemplate;

    private final ICrawlerClient crawlerClient;

    @Override
    public void executeMessage(UserQueueRequestDto request) {
        //performing the external API HTTP call (crawling)
        UserDto user = crawlerClient.fetchUser(request.getUserId());

        //publish the parsed result back to the web module via the result queue
        userResultQueueTemplate.convertAndSend(user);
    }
}
