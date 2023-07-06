package co.mobileaction.example.web.queue;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.web.service.IPostResultHandlerService;
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
public class PostResultQueueHandlerTests
{
    @InjectMocks
    private PostResultQueueHandler postResultQueueHandler;

    @Mock
    private IPostResultHandlerService service;

    @Mock(name = "resultProblemQueueTemplate")
    private AmqpTemplate resultProblemQueueTemplate;

    @Test
    public void handleMessage_success()
    {
        PostDto dto = new PostDto();

        postResultQueueHandler.handleMessage(dto);

        verify(service).executeMessage(dto);
    }

    @Test
    public void handleMessage_fail()
    {
        PostDto dto = new PostDto();

        doThrow(RuntimeException.class).when(service).executeMessage(dto);

        postResultQueueHandler.handleMessage(dto);

        verify(resultProblemQueueTemplate).convertAndSend(dto);
    }
}
