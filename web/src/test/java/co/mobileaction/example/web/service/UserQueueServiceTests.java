package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

/**
 * @author Mehmet Akif Sahin
 * @date 08.07.2024
 * @time 11:32
 */

@ExtendWith(MockitoExtension.class)
class UserQueueServiceTests
{
    @InjectMocks
    private UserQueueService userQueueService;

    @Mock(name = "userRequestQueueTemplate")
    private AmqpTemplate userRequestQueueTemplate;

    @Test
    void sendUserRequests()
    {
        List<Long> userIds = List.of(1L, 2L, 3L);

        userQueueService.sendUserRequests(userIds);

        for (Long userId : userIds)
        {
            verify(userRequestQueueTemplate).convertAndSend(argThat(dto -> ((UserQueueRequestDto) dto).getUserId().equals(userId)));
        }
    }
}
