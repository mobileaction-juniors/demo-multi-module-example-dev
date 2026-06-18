package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
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

@ExtendWith(MockitoExtension.class)
public class UserQueueServiceTests
{
    @InjectMocks
    private UserQueueService userQueueService;

    @Mock
    private AmqpTemplate userRequestQueueTemplate;

    @Mock
    private IPostService postService;

    @Captor
    private ArgumentCaptor<UserQueueRequestDto> requestCaptor;

    @Test
    public void sendUserRequestForAllItems()
    {
        List<Long> userIds = Arrays.asList(1L, 2L, 3L);
        when(postService.findDistinctUserIds()).thenReturn(userIds);

        userQueueService.sendUserRequestForAllItems();

        verify(postService).findDistinctUserIds();
        verify(userRequestQueueTemplate, times(3)).convertAndSend(requestCaptor.capture());

        List<UserQueueRequestDto> sentRequests = requestCaptor.getAllValues();
        assertThat(sentRequests).hasSize(3);
        assertThat(sentRequests).extracting(UserQueueRequestDto::userId)
                .containsExactly(1L, 2L, 3L);
    }
}
