package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.common.dto.QueueRequestUserDto;
import co.mobileaction.example.worker.service.IPostRequestHandlerService;
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
public class UserRequestQueueHandlerTests {

    @InjectMocks
    private UserRequestQueueHandler userRequestQueueHandler;

    @Mock
    private IUserRequestHandlerService service;

    @Mock(name = "requestProblemQueueTemplate")
    private AmqpTemplate requestProblemQueueTemplate;

    @Test
    public void handleMessage_success()
    {
        QueueRequestUserDto dto = new QueueRequestUserDto(1L);

        userRequestQueueHandler.handleMessage(dto);

        verify(service).executeMessage(dto);
    }

    @Test
    public void handleMessage_fail()
    {
        QueueRequestUserDto dto = new QueueRequestUserDto(1L);

        doThrow(RuntimeException.class).when(service).executeMessage(dto);

        userRequestQueueHandler.handleMessage(dto);

        verify(requestProblemQueueTemplate).convertAndSend(dto);
    }
}
