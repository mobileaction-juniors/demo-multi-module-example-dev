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

@ExtendWith(MockitoExtension.class)
public class UserResultQueueHandlerTests
{
    @InjectMocks
    private UserResultQueueHandler userResultQueueHandler;

    @Mock
    private IUserResultHandlerService userResultHandlerService;

    @Mock
    private AmqpTemplate resultProblemQueueTemplate;

    @Test
    public void handleMessage_success()
    {
        UserDto dto = new UserDto(1L, null, null, null);

        userResultQueueHandler.handleMessage(dto);

        verify(userResultHandlerService).executeMessage(dto);
    }

    @Test
    public void handleMessage_exception()
    {
        UserDto dto = new UserDto(1L, null, null, null);

        doThrow(RuntimeException.class).when(userResultHandlerService).executeMessage(dto);

        userResultQueueHandler.handleMessage(dto);

        verify(userResultHandlerService).executeMessage(dto);
        verify(resultProblemQueueTemplate).convertAndSend(dto);
    }
}
