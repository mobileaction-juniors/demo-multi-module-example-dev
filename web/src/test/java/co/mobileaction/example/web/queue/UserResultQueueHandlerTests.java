package co.mobileaction.example.web.queue;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.exception.AlreadyExistException;
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
 * @author sa
 * @date 17.05.2021
 * @time 19:11
 */
@ExtendWith(MockitoExtension.class)
public class UserResultQueueHandlerTests
{
    @InjectMocks
    private UserResultQueueHandler userResultQueueHandler;

    @Mock
    private IUserResultHandlerService service;

    @Mock(name = "userResultProblemQueueTemplate")
    private AmqpTemplate userResultProblemQueueTemplate;

    @Test
    public void handleMessage_success() throws AlreadyExistException
    {
        UserDto dto = new UserDto();

        userResultQueueHandler.handleMessage(dto);

        verify(service).executeMessage(dto);
    }

    @Test
    public void handleMessage_fail() throws AlreadyExistException
    {
        UserDto dto = new UserDto();

        doThrow(RuntimeException.class).when(service).executeMessage(dto);

        userResultQueueHandler.handleMessage(dto);

        verify(userResultProblemQueueTemplate).convertAndSend(dto);
    }
}
