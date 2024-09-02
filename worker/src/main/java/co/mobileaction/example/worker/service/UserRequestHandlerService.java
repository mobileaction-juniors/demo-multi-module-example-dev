package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.QueueRequestUserDto;
import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRequestHandlerService implements IUserRequestHandlerService {

    private final AmqpTemplate resultQueueTemplateUser;

    private final ICrawlerClient crawlerClient;

    @Override
    public void executeMessage(QueueRequestUserDto request) {

        UserDto user = crawlerClient.fetchUser(request.getUserId());

        resultQueueTemplateUser.convertAndSend(user);
    }
}
