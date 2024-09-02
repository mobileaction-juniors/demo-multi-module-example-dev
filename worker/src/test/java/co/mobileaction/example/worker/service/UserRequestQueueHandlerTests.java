package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.QueueRequestDto;
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
public class UserRequestQueueHandlerTests {

    @InjectMocks
    private UserRequestHandlerService requestHandlerService;

    @Mock
    private ICrawlerClient crawlerClient;

    @Mock
    private AmqpTemplate resultQueueTemplateUser;

    @Test
    public void test_crawlAppForAllCountries_missingApp()
    {
        UserDto user = UserDto.builder()
                .id(12L)
                .name("Hasan Mutlu")
                .email("hsnmtl@gmail.com")
                .phone("05544555555")
                .username("hsnmtl0044")
                .website("hsnmtl.com")
                .build();

        when(crawlerClient.fetchUser(eq(1L))).thenReturn(user);

        QueueRequestUserDto dto = new QueueRequestUserDto(1L);

        requestHandlerService.executeMessage(dto);

        verify(crawlerClient).fetchUser(1L);
        verify(resultQueueTemplateUser).convertAndSend(user);
    }
}
