package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.QueueUserRequestDto;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserQueueServiceTests
{
    @InjectMocks
    private UserQueueService userQueueService;

    @Mock
    private IPostService postService;

    @Mock
    private AmqpTemplate userRequestQueueTemplate;

    @Captor
    private ArgumentCaptor<QueueUserRequestDto> dtoArgumentCaptor;

    @Test
    public void sendUserRequestsForAllItems()
    {
        when(postService.findAllDistinctUsers()).thenReturn(List.of(1L, 2L, 3L));

        userQueueService.sendUserRequestsForAllItems();

        verify(userRequestQueueTemplate, times(3)).convertAndSend(dtoArgumentCaptor.capture());
        assertThat(dtoArgumentCaptor.getAllValues())
                .extracting(QueueUserRequestDto::getUserId)
                .containsExactlyInAnyOrder(1L, 2L, 3L);
    }
}
