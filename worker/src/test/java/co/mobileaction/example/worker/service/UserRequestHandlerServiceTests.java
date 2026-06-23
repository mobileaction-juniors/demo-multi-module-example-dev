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

/**
 * @author berkturk
 * @date 22.06.2026
 * @time 17:37
 */
@ExtendWith(MockitoExtension.class)
public class UserRequestHandlerServiceTests
{
    @InjectMocks
    private UserRequestHandlerService userRequestHandlerService;

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
                .phone("phone-1")
                .website("website-1")
                .build();

        when(crawlerClient.fetchUser(eq(1L))).thenReturn(user);

        UserQueueRequestDto dto = new UserQueueRequestDto(1L);

        userRequestHandlerService.executeMessage(dto);

        verify(crawlerClient).fetchUser(1L);
        verify(resultQueueTemplate).convertAndSend(user);
    }
}
