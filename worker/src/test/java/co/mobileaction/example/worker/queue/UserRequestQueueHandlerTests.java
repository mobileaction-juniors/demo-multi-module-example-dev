package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.worker.service.IUserRequestHandlerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserRequestQueueHandlerTests
{
    private final Long USER_ID = 1L;
    @InjectMocks
    private UserRequestQueueHandler userRequestQueueHandler;

    @Mock
    private IUserRequestHandlerService userRequestHandlerService;

    @Mock(name = "userRequestProblemQueueTemplate")
    private AmqpTemplate userRequestProblemQueueTemplate;

    @Test
    public void handleUserMessage_success()
    {
        UserQueueRequestDto userQueueRequestDto = new UserQueueRequestDto(USER_ID);
        userRequestQueueHandler.handleMessage(userQueueRequestDto);
        verify(userRequestHandlerService).executeMessage(userQueueRequestDto);
    }

    @Test
    public void handleUserMessage_fail()
    {
        UserQueueRequestDto userQueueRequestDto = new UserQueueRequestDto(USER_ID);
        doThrow(RuntimeException.class).when(userRequestHandlerService).executeMessage(userQueueRequestDto);
        userRequestQueueHandler.handleMessage(userQueueRequestDto);
        verify(userRequestProblemQueueTemplate).convertAndSend(userQueueRequestDto);
    }
}
