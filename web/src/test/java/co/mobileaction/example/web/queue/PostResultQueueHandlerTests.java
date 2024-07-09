package co.mobileaction.example.web.queue;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.service.IPostResultHandlerService;
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
public class PostResultQueueHandlerTests
{
    @InjectMocks
    private PostResultQueueHandler postResultQueueHandler;
    @InjectMocks
    private UserResultQueueHandler userResultQueueHandler;

    @Mock
    private IPostResultHandlerService service;
    @Mock
    private IUserResultHandlerService uServ;

    @Mock(name = "resultProblemQueueTemplate")
    private AmqpTemplate resultProblemQueueTemplate;
    @Mock(name = "resultProblemQueueUserTemplate")
    private AmqpTemplate resultProblemQueueUserTemplate;

    @Test
    public void handleMessage_success()
    {
        PostDto dto = new PostDto();

        postResultQueueHandler.handleMessage(dto);

        verify(service).executeMessage(dto);
    }

    @Test
    public void umes_success()
    {
        UserDto dto = new UserDto();

        userResultQueueHandler.handleMessage(dto);

        verify(uServ).executeMessage(dto);
    }
    @Test
    public void umes_fail()
    {
        UserDto dto = new UserDto();

        doThrow(RuntimeException.class).when(uServ).executeMessage(dto);

        userResultQueueHandler.handleMessage(dto);

        verify(resultProblemQueueTemplate).convertAndSend(dto);
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
