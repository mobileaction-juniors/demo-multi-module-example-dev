package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.worker.service.IUserRequestHandlerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRequestQueueHandlerTests
{
    @InjectMocks
    private UserRequestQueueHandler requestQueueHandler;

    @Mock(name = "userRequestProblemQueueTemplate")
    private AmqpTemplate userRequestProblemQueueTemplate;

    @Mock
    private IUserRequestHandlerService requestHandlerService;

    @Test
    void handleMessage_success()
    {
        UserQueueRequestDto dto = new UserQueueRequestDto(1L);

        requestQueueHandler.handleMessage(dto);

        verify(requestHandlerService).executeMessage(dto);
    }

    @Test
    void handleMessage_fail()
    {
        UserQueueRequestDto dto = new UserQueueRequestDto(1L);

        doThrow(RuntimeException.class).when(requestHandlerService).executeMessage(dto);

        requestQueueHandler.handleMessage(dto);

        verify(userRequestProblemQueueTemplate).convertAndSend(dto);
    }
}
