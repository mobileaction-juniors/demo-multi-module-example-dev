package co.mobileaction.example.web.queue;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.service.IUserResultHandlerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * @author berkturk
 * @date 22.06.2026
 * @time 17:22
 */
@ExtendWith(MockitoExtension.class)
public class UserResultQueueHandlerTests
{
    @InjectMocks
    private UserResultQueueHandler userResultQueueHandler;

    @Mock
    private IUserResultHandlerService service;

    @Mock(name = "resultProblemQueueTemplate")
    private AmqpTemplate resultProblemQueueTemplate;

    @Test
    public void handleMessage_success()
    {
        UserDto dto = new UserDto(1L, "name-1", "username-1", "phone-1", "email-1", "website-1");

        userResultQueueHandler.handleMessage(dto);

        verify(service).executeMessage(dto);
    }

    @Test
    public void handleMessage_fail()
    {
        UserDto dto = new UserDto(1L, "name-1", "username-1", "phone-1", "email-1", "website-1");

        doThrow(RuntimeException.class).when(service).executeMessage(dto);

        userResultQueueHandler.handleMessage(dto);

        verify(resultProblemQueueTemplate).convertAndSend(dto);
    }
}
