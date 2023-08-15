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
    private IUserResultHandlerService userService;

    @Mock(name = "userResultProblemQueueTemplate")
    private AmqpTemplate userResultProblemQueueTemplate;

    @Test
    public void userHandleMessage_success()
    {
        UserDto userDto = new UserDto();

        userResultQueueHandler.handleMessage(userDto);

        verify(userService).executeMessage(userDto);
    }

    @Test
    public void userHandleMessage_fail()
    {
        UserDto userDto = new UserDto();

        doThrow(RuntimeException.class).when(userService).executeMessage(userDto);

        userResultQueueHandler.handleMessage(userDto);

        verify(userResultProblemQueueTemplate).convertAndSend(userDto);
    }

}
