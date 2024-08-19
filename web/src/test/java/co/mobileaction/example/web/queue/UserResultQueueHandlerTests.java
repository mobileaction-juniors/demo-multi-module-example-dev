package co.mobileaction.example.web.queue;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.service.IUserResultHandlerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpTemplate;

import static org.mockito.Mockito.*;

class UserResultQueueHandlerTests {

    @Mock
    private AmqpTemplate userResultProblemQueueTemplate;

    @Mock
    private IUserResultHandlerService resultHandlerService;

    @InjectMocks
    private UserResultQueueHandler userResultQueueHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleMessage_success() {
        // Arrange
        UserDto mockUserDto = new UserDto();
        mockUserDto.setId(1L);

        // Act
        userResultQueueHandler.handleMessage(mockUserDto);

        // Assert
        verify(resultHandlerService, times(1)).executeMessage(mockUserDto);
        verify(userResultProblemQueueTemplate, never()).convertAndSend(mockUserDto);
    }

    @Test
    void handleMessage_exception() {
        // Arrange
        UserDto mockUserDto = new UserDto();
        mockUserDto.setId(1L);

        doThrow(new RuntimeException("Test Exception")).when(resultHandlerService).executeMessage(mockUserDto);

        // Act
        userResultQueueHandler.handleMessage(mockUserDto);

        // Assert
        verify(resultHandlerService, times(1)).executeMessage(mockUserDto);
        verify(userResultProblemQueueTemplate, times(1)).convertAndSend(mockUserDto);
    }
}
