package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserCrawlRequestDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for UserRequestHandlerService
 */
@ExtendWith(MockitoExtension.class)
public class UserRequestHandlerServiceTests {

    @Mock
    private AmqpTemplate userResultQueueTemplate;

    @Mock
    private ICrawlerClient crawlerClient;

    @InjectMocks
    private UserRequestHandlerService userRequestHandlerService;

    @Captor
    private ArgumentCaptor<UserCrawlRequestDto> userResultCaptor;

    private UserCrawlRequestDto testRequest;
    private UserCrawlRequestDto testUserResult;

    @BeforeEach
    void setUp() {
        testRequest = UserCrawlRequestDto.builder()
                .id(1L)
                .build();

        testUserResult = UserCrawlRequestDto.builder()
                .id(1L)
                .name("John Doe")
                .username("johndoe")
                .build();
    }

    @Test
    void executeMessage_ShouldFetchUserAndSendToResultQueue() {
        // Given
        when(crawlerClient.fetchUser(eq(1L))).thenReturn(testUserResult);

        // When
        userRequestHandlerService.executeMessage(testRequest);

        // Then
        verify(crawlerClient).fetchUser(1L);
        verify(userResultQueueTemplate).convertAndSend(userResultCaptor.capture());
        
        UserCrawlRequestDto capturedResult = userResultCaptor.getValue();
        assertThat(capturedResult.getId()).isEqualTo(1L);
        assertThat(capturedResult.getName()).isEqualTo("John Doe");
        assertThat(capturedResult.getUsername()).isEqualTo("johndoe");
    }

    @Test
    void executeMessage_ShouldHandleUserWithNullFields() {
        // Given
        UserCrawlRequestDto userResultWithNulls = UserCrawlRequestDto.builder()
                .id(2L)
                .name(null)
                .username(null)
                .build();
        
        UserCrawlRequestDto requestWithNulls = UserCrawlRequestDto.builder()
                .id(2L)
                .build();
        
        when(crawlerClient.fetchUser(eq(2L))).thenReturn(userResultWithNulls);

        // When
        userRequestHandlerService.executeMessage(requestWithNulls);

        // Then
        verify(crawlerClient).fetchUser(2L);
        verify(userResultQueueTemplate).convertAndSend(userResultCaptor.capture());
        
        UserCrawlRequestDto capturedResult = userResultCaptor.getValue();
        assertThat(capturedResult.getId()).isEqualTo(2L);
        assertThat(capturedResult.getName()).isNull();
        assertThat(capturedResult.getUsername()).isNull();
    }

    @Test
    void executeMessage_ShouldHandleUserWithEmptyStrings() {
        // Given
        UserCrawlRequestDto userResultWithEmptyStrings = UserCrawlRequestDto.builder()
                .id(3L)
                .name("")
                .username("")
                .build();
        
        UserCrawlRequestDto requestWithEmptyStrings = UserCrawlRequestDto.builder()
                .id(3L)
                .build();
        
        when(crawlerClient.fetchUser(eq(3L))).thenReturn(userResultWithEmptyStrings);

        // When
        userRequestHandlerService.executeMessage(requestWithEmptyStrings);

        // Then
        verify(crawlerClient).fetchUser(3L);
        verify(userResultQueueTemplate).convertAndSend(userResultCaptor.capture());
        
        UserCrawlRequestDto capturedResult = userResultCaptor.getValue();
        assertThat(capturedResult.getId()).isEqualTo(3L);
        assertThat(capturedResult.getName()).isEqualTo("");
        assertThat(capturedResult.getUsername()).isEqualTo("");
    }

    @Test
    void executeMessage_ShouldHandleLargeUserId() {
        // Given
        UserCrawlRequestDto userResultWithLargeId = UserCrawlRequestDto.builder()
                .id(999999999L)
                .name("Large ID User")
                .username("largeiduser")
                .build();
        
        UserCrawlRequestDto requestWithLargeId = UserCrawlRequestDto.builder()
                .id(999999999L)
                .build();
        
        when(crawlerClient.fetchUser(eq(999999999L))).thenReturn(userResultWithLargeId);

        // When
        userRequestHandlerService.executeMessage(requestWithLargeId);

        // Then
        verify(crawlerClient).fetchUser(999999999L);
        verify(userResultQueueTemplate).convertAndSend(userResultCaptor.capture());
        
        UserCrawlRequestDto capturedResult = userResultCaptor.getValue();
        assertThat(capturedResult.getId()).isEqualTo(999999999L);
        assertThat(capturedResult.getName()).isEqualTo("Large ID User");
        assertThat(capturedResult.getUsername()).isEqualTo("largeiduser");
    }

    @Test
    void executeMessage_ShouldHandleSpecialCharactersInUserData() {
        // Given
        UserCrawlRequestDto userResultWithSpecialChars = UserCrawlRequestDto.builder()
                .id(4L)
                .name("José María")
                .username("user@123")
                .build();
        
        UserCrawlRequestDto requestWithSpecialChars = UserCrawlRequestDto.builder()
                .id(4L)
                .build();
        
        when(crawlerClient.fetchUser(eq(4L))).thenReturn(userResultWithSpecialChars);

        // When
        userRequestHandlerService.executeMessage(requestWithSpecialChars);

        // Then
        verify(crawlerClient).fetchUser(4L);
        verify(userResultQueueTemplate).convertAndSend(userResultCaptor.capture());
        
        UserCrawlRequestDto capturedResult = userResultCaptor.getValue();
        assertThat(capturedResult.getId()).isEqualTo(4L);
        assertThat(capturedResult.getName()).isEqualTo("José María");
        assertThat(capturedResult.getUsername()).isEqualTo("user@123");
    }
} 