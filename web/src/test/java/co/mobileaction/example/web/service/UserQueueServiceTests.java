package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.QueueRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author sa
 * @date 17.05.2021
 * @time 18:10
 */
@ExtendWith(MockitoExtension.class)
class UserQueueServiceTests
{
    @Mock
    private AmqpTemplate userRequestQueueTemplate;

    @Mock
    private IPostService postService;

    @InjectMocks
    private UserQueueService userQueueService;

    @Test
    void sendUserRequestForAllItems_ShouldSendRequestsForDistinctUserIds()
    {
        List<Long> distinctUserIds = Arrays.asList(1L, 2L, 3L, 5L, 10L);
        when(postService.findDistinctUserIds()).thenReturn(distinctUserIds);

        userQueueService.sendUserRequestForAllItems();

        ArgumentCaptor<QueueRequestDto> requestCaptor = ArgumentCaptor.forClass(QueueRequestDto.class);
        verify(userRequestQueueTemplate, times(5)).convertAndSend(requestCaptor.capture());

        List<QueueRequestDto> capturedRequests = requestCaptor.getAllValues();
        assertThat(capturedRequests).hasSize(5);
        
        assertThat(capturedRequests)
                .extracting(QueueRequestDto::getUserId)
                .containsExactlyInAnyOrder(1L, 2L, 3L, 5L, 10L);
        
        assertThat(capturedRequests)
                .extracting(QueueRequestDto::getPostId)
                .containsOnlyNulls();
    }

    @Test
    void sendUserRequestForAllItems_ShouldHandleEmptyUserIdsList()
    {
        when(postService.findDistinctUserIds()).thenReturn(Collections.emptyList());

        userQueueService.sendUserRequestForAllItems();

        verify(userRequestQueueTemplate, never()).convertAndSend(any());
    }

    @Test
    void sendUserRequestForAllItems_ShouldHandleSingleUserId()
    {
        List<Long> distinctUserIds = Arrays.asList(42L);
        when(postService.findDistinctUserIds()).thenReturn(distinctUserIds);

        userQueueService.sendUserRequestForAllItems();

        ArgumentCaptor<QueueRequestDto> requestCaptor = ArgumentCaptor.forClass(QueueRequestDto.class);
        verify(userRequestQueueTemplate, times(1)).convertAndSend(requestCaptor.capture());

        QueueRequestDto capturedRequest = requestCaptor.getValue();
        assertThat(capturedRequest.getUserId()).isEqualTo(42L);
        assertThat(capturedRequest.getPostId()).isNull();
    }

    @Test
    void sendUserRequestForAllItems_ShouldCallPostServiceOnce()
    {
        List<Long> distinctUserIds = Arrays.asList(1L, 2L);
        when(postService.findDistinctUserIds()).thenReturn(distinctUserIds);

        userQueueService.sendUserRequestForAllItems();

        verify(postService, times(1)).findDistinctUserIds();
    }
} 