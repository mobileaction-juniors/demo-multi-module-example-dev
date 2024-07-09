package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.common.dto.UserQueueRequestDto;
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
public class PostRequestQueueHandlerTests
{
    @InjectMocks
    private PostRequestQueueHandler postRequestQueueHandler;
    @InjectMocks
    private UserRequestQueueHandler userRequestQueueHandler;//should be in another file

    @Mock
    private IPostRequestHandlerService service;
    @Mock
    private IUserRequestHandlerService uService;//should be in another file

    @Mock(name = "requestProblemQueueTemplate")
    private AmqpTemplate requestProblemQueueTemplate;
    @Mock(name = "requestProblemQueueUserTemplate")
    private AmqpTemplate requestProblemQueueUserTemplate;//should be in another file

    @Test
    public void handleMessage_success()
    {
        QueueRequestDto dto = new QueueRequestDto(1L);
        UserQueueRequestDto uDto = new UserQueueRequestDto(1L);//should be in another file

        userRequestQueueHandler.handleMessage(uDto);//should be in another file
        postRequestQueueHandler.handleMessage(dto);

        verify(uService).executeMessage(uDto);//should be in another file
        verify(service).executeMessage(dto);
    }

    @Test
    public void handleMessage_fail()
    {
        QueueRequestDto dto = new QueueRequestDto(1L);

        doThrow(RuntimeException.class).when(service).executeMessage(dto);

        postRequestQueueHandler.handleMessage(dto);

        verify(requestProblemQueueTemplate).convertAndSend(dto);
    }

    @Test
    public void handleMessageUser_fail()
    {//should be in another file
        UserQueueRequestDto uDto = new UserQueueRequestDto(1L);
        doThrow(RuntimeException.class).when(uService).executeMessage(uDto);
        userRequestQueueHandler.handleMessage(uDto);
        verify(requestProblemQueueUserTemplate).convertAndSend(uDto);
    }
}
