package co.mobileaction.example.web.service;


import co.mobileaction.example.common.dto.UserQueueRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService
{
    private final AmqpTemplate userRequestQueueTemplate;
    private final IUserService userService;

    @Override
    public void sendUserRequestForAllItems()
    {
        List<Long> distinctUserIds = userService.getAllDistinctUserIds();

        distinctUserIds.stream()
                .map(UserQueueRequestDto::new)
                .forEach(userRequestQueueTemplate::convertAndSend);

    }

}