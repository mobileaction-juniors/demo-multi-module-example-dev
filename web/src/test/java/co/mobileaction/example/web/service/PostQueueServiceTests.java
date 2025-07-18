package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.QueueRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for PostQueueService
 */
@ExtendWith(MockitoExtension.class)
public class PostQueueServiceTests {

    @Mock
    private AmqpTemplate requestQueueTemplate;

    @InjectMocks
    private PostQueueService postQueueService;

    @Captor
    private ArgumentCaptor<QueueRequestDto> queueRequestCaptor;

    @Test
    void sendPostRequestForAllItems_ShouldSend100Requests() {
        // When
        postQueueService.sendPostRequestForAllItems();

        // Then
        verify(requestQueueTemplate, times(100)).convertAndSend(queueRequestCaptor.capture());
        
        List<QueueRequestDto> capturedRequests = queueRequestCaptor.getAllValues();
        assertThat(capturedRequests).hasSize(100);
        
        // Verify the first request
        assertThat(capturedRequests.get(0).getPostId()).isEqualTo(1L);
        
        // Verify the last request
        assertThat(capturedRequests.get(99).getPostId()).isEqualTo(100L);
        
        // Verify a few random requests in between
        assertThat(capturedRequests.get(49).getPostId()).isEqualTo(50L);
        assertThat(capturedRequests.get(24).getPostId()).isEqualTo(25L);
        assertThat(capturedRequests.get(74).getPostId()).isEqualTo(75L);
    }

    @Test
    void sendPostRequestForAllItems_ShouldSendSequentialIds() {
        // When
        postQueueService.sendPostRequestForAllItems();

        // Then
        verify(requestQueueTemplate, times(100)).convertAndSend(queueRequestCaptor.capture());
        
        List<QueueRequestDto> capturedRequests = queueRequestCaptor.getAllValues();
        
        // Verify that IDs are sequential from 1 to 100
        for (int i = 0; i < 100; i++) {
            assertThat(capturedRequests.get(i).getPostId()).isEqualTo((long) (i + 1));
        }
    }
} 