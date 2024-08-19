package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.UserQueueDto;
import co.mobileaction.example.worker.service.IUserRequestHandlerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRequestQueueHandlerTests {

    @InjectMocks
    private UserRequestQueueHandler userRequestQueueHandler;

    @Mock
    private AmqpTemplate userRequestProblemTemplate;

    @Mock
    private IUserRequestHandlerService requestHandlerService;

    @Test
    public void test_handleMessage_success() {
        // Arrange
        UserQueueDto request = new UserQueueDto();
        request.setId(1L); // Set other necessary fields if any

        // Act
        userRequestQueueHandler.handleMessage(request);

        // Assert
        verify(requestHandlerService, times(1)).executeMessage(request);
        verify(userRequestProblemTemplate, times(0)).convertAndSend(any(UserQueueDto.class));
    }

    @Test
    public void test_handleMessage_failure() {
        // Arrange
        UserQueueDto request = new UserQueueDto();
        request.setId(1L); // Set other necessary fields if any
        doThrow(new RuntimeException("Test exception")).when(requestHandlerService).executeMessage(request);

        // Act
        userRequestQueueHandler.handleMessage(request);

        // Assert
        verify(requestHandlerService, times(1)).executeMessage(request);
        verify(userRequestProblemTemplate, times(1)).convertAndSend(request);
    }
}
