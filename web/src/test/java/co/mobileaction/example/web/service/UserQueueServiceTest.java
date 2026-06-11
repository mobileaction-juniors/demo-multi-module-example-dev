package co.mobileaction.example.web.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.web.repository.IPostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

@ExtendWith(MockitoExtension.class)
class UserQueueServiceTest {

    @Mock
    private IPostRepository postRepository;

    @Mock
    private AmqpTemplate userRequestQueueTemplate;

    @InjectMocks
    private UserQueueService userQueueService;

    //test for sending requests for distinct users to the worker module via the request queue
    @Test
    void sendsRequestsForDistinctUsers() {
        when(postRepository.findDistinctUserIds()).thenReturn(Arrays.asList(1L, 2L));

        userQueueService.sendCrawlRequestForDistinctUsers();

        ArgumentCaptor<UserQueueRequestDto> captor = ArgumentCaptor.forClass(UserQueueRequestDto.class);
        verify(userRequestQueueTemplate, times(2)).convertAndSend(captor.capture());

        List<Long> sentIds = captor.getAllValues().stream().map(UserQueueRequestDto::getUserId).collect(Collectors.toList());
        assertEquals(2, sentIds.size());
        assertTrue(sentIds.containsAll(Arrays.asList(1L, 2L)));
    }
}
