package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.worker.service.IPostRequestHandlerService;
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
 * @date 18.05.2021
 * @time 10:32
 */
@ExtendWith(MockitoExtension.class)
public class PostRequestQueueHandlerTests
{
    @InjectMocks
    private PostRequestQueueHandler postRequestQueueHandler;

    @Mock
    private IPostRequestHandlerService service;

    @Mock(name = "requestProblemQueueTemplate")
    private AmqpTemplate requestProblemQueueTemplate;

    @Test
    public void handleMessage_success()
    {
        QueueRequestDto dto = new QueueRequestDto(1L);

        postRequestQueueHandler.handleMessage(dto);

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


}
