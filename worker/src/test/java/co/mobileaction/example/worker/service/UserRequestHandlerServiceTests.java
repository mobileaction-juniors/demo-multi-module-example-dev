package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.common.dto.UserQueueRequestDto;
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

@ExtendWith(MockitoExtension.class)
public class UserRequestHandlerServiceTests
{
    @InjectMocks
    private UserRequestHandlerService userRequestHandlerService;

    @Mock
    private ICrawlerClient userCrawlerClient;

    @Mock
    private AmqpTemplate userQueueTemplate;


    @Test
    public void executeMessage()
    {
        Long id = 1L;
        UserDto userDto = UserDto.builder()
                .id(id)
                .name("name-1")
                .username("username-1")
                .email("email-1")
                .build();

        when(userCrawlerClient.fetchUser(eq(1L))).thenReturn(userDto);

        UserQueueRequestDto userQueueRequestDto = new UserQueueRequestDto(1L);

        userRequestHandlerService.executeMessage(userQueueRequestDto);

        verify(userCrawlerClient).fetchUser(1L);
        verify(userQueueTemplate).convertAndSend(userDto);
    }
}
