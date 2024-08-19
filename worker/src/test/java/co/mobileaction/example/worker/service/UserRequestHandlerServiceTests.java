package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.common.dto.UserQueueDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserRequestHandlerServiceTests {

    @InjectMocks
    private UserRequestHandlerService userRequestHandlerService;

    @Mock
    private AmqpTemplate resultUserQueueTemplate;

    @Mock
    private ICrawlerClient crawlerClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_executeMessage_sendsCorrectData() {
        // Arrange
        long userId = 1L;
        UserQueueDto request = new UserQueueDto();
        request.setId(userId);

        UserDto userDto = new UserDto();
        userDto.setId(userId);

        when(crawlerClient.fetchUser(userId)).thenReturn(userDto);

        // Act
        userRequestHandlerService.executeMessage(request);

        // Assert
        verify(crawlerClient, times(1)).fetchUser(userId);
        verify(resultUserQueueTemplate, times(1)).convertAndSend(userDto);
    }
}
