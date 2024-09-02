package co.mobileaction.example.web.queue;


import co.mobileaction.example.common.dto.PostDto;
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

@ExtendWith(MockitoExtension.class)
public class UserResultQueueHandlerTests {

    @InjectMocks
    private UserResultQueueHandler userResultQueueHandler;

    @Mock
    private IUserResultHandlerService userResultHandlerService;

    @Mock(name = "resultProblemQueueTemplate")
    private AmqpTemplate resultProblemQueueTemplate;

    @Test
    public void handleMessage_success(){
        UserDto userDto = new UserDto();

        userResultQueueHandler.handleMessage(userDto);

        verify(userResultHandlerService).executeMessage(userDto);

    }

    @Test
    public void handleMessage_fail()
    {
        UserDto userDto = new UserDto();

        doThrow(RuntimeException.class).when(userResultHandlerService).executeMessage(userDto);

        userResultQueueHandler.handleMessage(userDto);

        verify(resultProblemQueueTemplate).convertAndSend(userDto);
    }
}
