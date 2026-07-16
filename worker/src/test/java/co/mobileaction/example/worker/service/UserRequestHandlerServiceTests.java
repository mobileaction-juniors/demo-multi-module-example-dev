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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRequestHandlerServiceTests
{
    @InjectMocks
    private UserRequestHandlerService requestHandlerService;

    @Mock
    private ICrawlerClient crawlerClient;

    @Mock(name = "userResultQueueTemplate")
    private AmqpTemplate userResultQueueTemplate;

    @Test
    public void executeMessage()
    {
        UserDto user = UserDto.builder()
                .id(1L)
                .name("name-1")
                .username("username-1")
                .build();

        when(crawlerClient.fetchUser(1L)).thenReturn(user);

        UserQueueRequestDto request = new UserQueueRequestDto(1L);

        requestHandlerService.executeMessage(request);

        verify(crawlerClient).fetchUser(1L);
        verify(userResultQueueTemplate).convertAndSend(user);
    }
}
