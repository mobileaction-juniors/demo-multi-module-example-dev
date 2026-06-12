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
    private ArgumentCaptor<UserQueueRequestDto> dtoCaptor;

    @Test
    public void sendUserRequestForDistinctUserIds()
    {
        when(postService.findDistinctUserIds()).thenReturn(List.of(1L, 2L));

        userQueueService.sendUserRequestForDistinctUserIds();

        verify(userRequestQueueTemplate, times(2)).convertAndSend(dtoCaptor.capture());
        assertThat(dtoCaptor.getAllValues()).extracting(UserQueueRequestDto::getUserId)
                .containsExactly(1L, 2L);
    }
}
