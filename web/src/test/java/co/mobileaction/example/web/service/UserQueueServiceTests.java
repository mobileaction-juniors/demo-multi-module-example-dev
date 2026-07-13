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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserQueueServiceTests
{
    @InjectMocks
    private UserQueueService userQueueService;

    @Mock(name = "userRequestQueueTemplate")
    private AmqpTemplate userRequestQueueTemplate;

    @Mock
    private IPostService postService;

    @Captor
    private ArgumentCaptor<UserQueueRequestDto> requestCaptor;

    @Test
    public void sendUserRequestForAllItems()
    {
        when(postService.findDistinctUserIds()).thenReturn(List.of(1L, 2L, 3L));

        userQueueService.sendUserRequestForAllItems();

        verify(postService).findDistinctUserIds();
        verify(userRequestQueueTemplate, times(3)).convertAndSend(requestCaptor.capture());
        assertThat(requestCaptor.getAllValues())
                .extracting(UserQueueRequestDto::getUserId)
                .containsExactly(1L, 2L, 3L);
    }

    @Test
    public void sendUserRequestForNoItems()
    {
        when(postService.findDistinctUserIds()).thenReturn(List.of());

        userQueueService.sendUserRequestForAllItems();

        verify(postService).findDistinctUserIds();
        verifyNoInteractions(userRequestQueueTemplate);
    }
}
