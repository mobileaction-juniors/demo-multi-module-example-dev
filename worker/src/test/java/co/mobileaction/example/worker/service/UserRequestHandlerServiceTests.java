package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
import co.mobileaction.example.worker.service.User.UserRequestHandlerService;
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
    private UserRequestHandlerService requestHandlerService;

    @Mock
    private ICrawlerClient crawlerClient;

    @Mock
    private AmqpTemplate resultQueueTemplate;

    @Test
    public void test_crawlAppForAllCountries_missingApp()
    {
        UserDto user = UserDto.builder()
                .id(1L)
                .name("name-1")
                .username("username-1")
                .email("email-1")
                .build();

        when(crawlerClient.fetchUser(eq(1L))).thenReturn(user);

        QueueRequestDto dto = new QueueRequestDto(1L);

        requestHandlerService.executeMessage(dto);

        verify(crawlerClient).fetchUser(1L);
        verify(resultQueueTemplate).convertAndSend(user);
    }
}
