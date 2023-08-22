package co.mobileaction.example.web.service;


import co.mobileaction.example.common.dto.QueueUserDto;
import co.mobileaction.example.web.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService{
    private final IUserService userService;
    private final AmqpTemplate userRequestQueueTemplate;

    @Override
    public void sendUserRequestForAllItems()
    {

        List<Long> userIds = userService.getAllDistinctIds();
        for (Long userId : userIds) {
        userRequestQueueTemplate.convertAndSend(new QueueUserDto(userId));
        }

    }


}
