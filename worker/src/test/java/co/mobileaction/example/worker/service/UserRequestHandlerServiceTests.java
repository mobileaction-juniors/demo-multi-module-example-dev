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

/**
 * @author Mehmet Akif Sahin
 * @date 08.07.2024
 * @time 11:16
 */
@ExtendWith(MockitoExtension.class)
class UserRequestHandlerServiceTests
{
    @InjectMocks
    UserRequestHandlerService requestHandlerService;

    @Mock(name = "userResultQueueTemplate")
    private AmqpTemplate userResultQueueTemplate;

    @Mock
    private ICrawlerClient crawlerClient;

    @Test
    void executeMessage()
    {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .name("name-1")
                .username("username-1")
                .email("email-1")
                .build();
        UserQueueRequestDto userQueueRequestDto = new UserQueueRequestDto(1L);

        when(crawlerClient.fetchUser(1L)).thenReturn(userDto);

        requestHandlerService.executeMessage(userQueueRequestDto);

        verify(crawlerClient).fetchUser(1L);
        verify(userResultQueueTemplate).convertAndSend(userDto);
    }
}
