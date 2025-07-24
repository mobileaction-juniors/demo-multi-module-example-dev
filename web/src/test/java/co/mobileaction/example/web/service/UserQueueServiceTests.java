package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserCrawlRequestDto;
import co.mobileaction.example.web.repository.IPostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for UserQueueService
 */
@ExtendWith(MockitoExtension.class)
public class UserQueueServiceTests {

    @Mock
    private AmqpTemplate userRequestQueueTemplate;

    @Mock
    private IPostRepository postRepository;

    @InjectMocks
    private UserQueueService userQueueService;

    @Captor
    private ArgumentCaptor<UserCrawlRequestDto> userRequestCaptor;

    @Test
    void sendUserRequestForAllItems_ShouldSendRequestsForAllDistinctUserIds() {
        // Given
        List<Long> userIds = Arrays.asList(1L, 2L, 3L, 5L, 8L);
        when(postRepository.findDistinctUserIds()).thenReturn(userIds);

        // When
        userQueueService.sendUserRequestForAllItems();

        // Then
        verify(postRepository).findDistinctUserIds();
        verify(userRequestQueueTemplate, times(5)).convertAndSend(userRequestCaptor.capture());
        
        List<UserCrawlRequestDto> capturedRequests = userRequestCaptor.getAllValues();
        assertThat(capturedRequests).hasSize(5);
        
        // Verify each request has the correct user ID
        assertThat(capturedRequests.get(0).getId()).isEqualTo(1L);
        assertThat(capturedRequests.get(1).getId()).isEqualTo(2L);
        assertThat(capturedRequests.get(2).getId()).isEqualTo(3L);
        assertThat(capturedRequests.get(3).getId()).isEqualTo(5L);
        assertThat(capturedRequests.get(4).getId()).isEqualTo(8L);
    }

    @Test
    void sendUserRequestForAllItems_ShouldHandleEmptyUserIdsList() {
        // Given
        when(postRepository.findDistinctUserIds()).thenReturn(Arrays.asList());

        // When
        userQueueService.sendUserRequestForAllItems();

        // Then
        verify(postRepository).findDistinctUserIds();
        verify(userRequestQueueTemplate, times(0)).convertAndSend(userRequestCaptor.capture());
        
        List<UserCrawlRequestDto> capturedRequests = userRequestCaptor.getAllValues();
        assertThat(capturedRequests).isEmpty();
    }

    @Test
    void sendUserRequestForAllItems_ShouldHandleSingleUserId() {
        // Given
        List<Long> userIds = Arrays.asList(1L);
        when(postRepository.findDistinctUserIds()).thenReturn(userIds);

        // When
        userQueueService.sendUserRequestForAllItems();

        // Then
        verify(postRepository).findDistinctUserIds();
        verify(userRequestQueueTemplate, times(1)).convertAndSend(userRequestCaptor.capture());
        
        List<UserCrawlRequestDto> capturedRequests = userRequestCaptor.getAllValues();
        assertThat(capturedRequests).hasSize(1);
        assertThat(capturedRequests.get(0).getId()).isEqualTo(1L);
    }

    @Test
    void sendUserRequestForAllItems_ShouldCreateCorrectUserCrawlRequestDto() {
        // Given
        List<Long> userIds = Arrays.asList(1L, 2L);
        when(postRepository.findDistinctUserIds()).thenReturn(userIds);

        // When
        userQueueService.sendUserRequestForAllItems();

        // Then
        verify(userRequestQueueTemplate, times(2)).convertAndSend(userRequestCaptor.capture());
        
        List<UserCrawlRequestDto> capturedRequests = userRequestCaptor.getAllValues();
        
        // Verify the structure of the DTOs
        UserCrawlRequestDto firstRequest = capturedRequests.get(0);
        assertThat(firstRequest.getId()).isEqualTo(1L);
        assertThat(firstRequest.getName()).isNull();
        assertThat(firstRequest.getUsername()).isNull();
        
        UserCrawlRequestDto secondRequest = capturedRequests.get(1);
        assertThat(secondRequest.getId()).isEqualTo(2L);
        assertThat(secondRequest.getName()).isNull();
        assertThat(secondRequest.getUsername()).isNull();
    }
} 