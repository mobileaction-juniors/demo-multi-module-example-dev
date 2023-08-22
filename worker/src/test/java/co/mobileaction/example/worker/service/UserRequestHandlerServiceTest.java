package co.mobileaction.example.worker.service;

import co.mobileaction.example.worker.client.ICrawlerClient;
import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.common.dto.QueueUserDto;
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
public class UserRequestHandlerServiceTest {

        @InjectMocks
        private UserRequestHandlerService requestHandlerService;

        @Mock
        private ICrawlerClient crawlerClient;

        @Mock
        private AmqpTemplate userRequestQueueHandler;

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

            QueueUserDto dto = new QueueUserDto(1L);


            requestHandlerService.executeUser(dto);

            verify(crawlerClient).fetchUser(1L);
            verify(userRequestQueueHandler).convertAndSend(user);
        }

}
