package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.QueueRequestUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService {

    private final AmqpTemplate requestQueueTemplateUser;

    @Override
    public void sendUserIdsForAllItems(List<Long> ids) {

        ids.stream()
                .map(QueueRequestUserDto::new)
                .forEach(requestQueueTemplateUser::convertAndSend);

    }
}
