package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.QueueRequestUserDto;
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

@ExtendWith(MockitoExtension.class)
public class UserRequestHandlerServiceTests {

    @InjectMocks
    private UserRequestHandlerService userRequestHandlerService;

    @Mock
    private ICrawlerClient crawlerClient;

    @Mock
    private AmqpTemplate userResultQueueTemplate;

    @Test
    public void test_crawlAppForAllCountries_missingApp()
    {
        UserDto user = UserDto.builder()
                .id(1L)
                .name("name-1")
                .username("username-1")
                .email("email-1")
                .phone("phone-1")
                .build();

        when(crawlerClient.fetchUser(eq(1L))).thenReturn(user);

        QueueRequestUserDto dto = new QueueRequestUserDto(1L);

        userRequestHandlerService.executeMessage(dto);

        verify(crawlerClient).fetchUser(1L);
        verify(userResultQueueTemplate).convertAndSend(user);
    }

}
