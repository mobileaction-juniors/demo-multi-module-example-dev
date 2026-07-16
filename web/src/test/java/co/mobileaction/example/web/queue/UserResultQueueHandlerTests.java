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
import static org.mockito.Mockito.verifyNoInteractions;

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
    public void handleMessage_success()
    {
        UserDto user = new UserDto();

        userResultQueueHandler.handleMessage(user);

        verify(service).executeMessage(user);
        verifyNoInteractions(userResultProblemQueueTemplate);
    }

    @Test
    public void handleMessage_fail()
    {
        UserDto user = new UserDto();

        doThrow(RuntimeException.class).when(service).executeMessage(user);

        userResultQueueHandler.handleMessage(user);

        verify(userResultProblemQueueTemplate).convertAndSend(user);
    }
}
