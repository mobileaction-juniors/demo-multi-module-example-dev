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
 * @author serkankorkut
 * @date 12.06.2026
 * @time 15:22
 */
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
    public void test_crawlUser()
    {
        UserDto user = UserDto.builder()
                .id(1L)
                .name("name-1")
                .username("username-1")
                .email("email-1@example.com")
                .build();

        when(crawlerClient.fetchUser(eq(1L))).thenReturn(user);

        UserQueueRequestDto dto = new UserQueueRequestDto(1L);

        requestHandlerService.executeMessage(dto);

        verify(crawlerClient).fetchUser(1L);
        verify(userResultQueueTemplate).convertAndSend(user);
    }
}
