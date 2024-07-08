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
 * @author Mehmet Akif Sahin
 * @date 08.07.2024
 * @time 09:46
 */
@ExtendWith(MockitoExtension.class)
class UserResultQueueHandlerTests
{
    @InjectMocks
    private UserResultQueueHandler userResultQueueHandler;

    @Mock(name = "userResultProblemQueueTemplate")
    private AmqpTemplate userResultProblemQueueTemplate;

    @Mock
    private IUserResultHandlerService userResultHandlerService;

    @Test
    void handleMessage_success()
    {
        UserDto userDto = new UserDto();

        userResultQueueHandler.handleMessage(userDto);

        verify(userResultHandlerService).executeMessage(userDto);
    }

    @Test
    void handleMessage_fail()
    {
        UserDto userDto = new UserDto();

        doThrow(RuntimeException.class).when(userResultHandlerService).executeMessage(userDto);

        userResultQueueHandler.handleMessage(userDto);

        verify(userResultProblemQueueTemplate).convertAndSend(userDto);
    }
}
