package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author sa
 * @date 18.05.2021
 * @time 10:37
 */
@ExtendWith(MockitoExtension.class)
public class UserRequestHandlerServiceTests
{
    @InjectMocks
    private UserRequestHandlerService userRequestHandlerService;

    @Mock
    private ICrawlerClient crawlerClient;

    @Mock
    private AmqpTemplate userResultQueueTemplate;

    @Test
    public void executeMessage_ShouldFetchUserAndSendToResultQueue()
    {
        Long userId = 1L;
        QueueRequestDto request = QueueRequestDto.builder()
                .userId(userId)
                .build();

        UserDto expectedUser = UserDto.builder()
                .id(userId)
                .name("Leanne Graham")
                .username("Bret")
                .build();

        when(crawlerClient.fetchUser(eq(userId))).thenReturn(expectedUser);

        userRequestHandlerService.executeMessage(request);

        verify(crawlerClient).fetchUser(userId);
        verify(userResultQueueTemplate).convertAndSend(expectedUser);
    }

    @Test
    public void executeMessage_ShouldHandleDifferentUserIds()
    {
        Long userId = 5L;
        QueueRequestDto request = QueueRequestDto.builder()
                .userId(userId)
                .build();

        UserDto expectedUser = UserDto.builder()
                .id(userId)
                .name("Chelsey Dietrich")
                .username("Kamren")
                .build();

        when(crawlerClient.fetchUser(eq(userId))).thenReturn(expectedUser);

        userRequestHandlerService.executeMessage(request);

        verify(crawlerClient).fetchUser(userId);
        verify(userResultQueueTemplate).convertAndSend(expectedUser);
    }

    @Test
    public void executeMessage_ShouldHandleNullUserFromCrawler()
    {
        Long userId = 999L;
        QueueRequestDto request = QueueRequestDto.builder()
                .userId(userId)
                .build();

        when(crawlerClient.fetchUser(eq(userId))).thenReturn(null);

        userRequestHandlerService.executeMessage(request);

        verify(crawlerClient).fetchUser(userId);
        verify(userResultQueueTemplate).convertAndSend(null);
    }

    @Test
    public void executeMessage_ShouldUseCorrectUserId()
    {
        Long userId = 10L;
        QueueRequestDto request = QueueRequestDto.builder()
                .userId(userId)
                .postId(123L)
                .build();

        UserDto expectedUser = UserDto.builder()
                .id(userId)
                .name("Clementina DuBuque")
                .username("Moriah.Stanton")
                .build();

        when(crawlerClient.fetchUser(eq(userId))).thenReturn(expectedUser);

        userRequestHandlerService.executeMessage(request);

        verify(crawlerClient).fetchUser(userId);
        verify(userResultQueueTemplate).convertAndSend(expectedUser);
    }
} 