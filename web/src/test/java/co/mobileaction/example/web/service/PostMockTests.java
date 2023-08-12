package co.mobileaction.example.web.service;

import co.mobileaction.example.web.repository.IPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostMockTests
{
    @Mock
    private IPostRepository postRepository;

    @Mock
    private AmqpTemplate userRequestQueueTemplate;

    private PostService postService;

    private IUserQueueService userQueueService;

    @BeforeEach
    public void setUp()
    {
        userQueueService = new UserQueueService(userRequestQueueTemplate);
        postService = new PostService(postRepository, userQueueService);
    }

    @Test
    public void sendUserRequestFromPost()
    {
        List<Long> userIds = Arrays.asList(1L, 2L, 3L);

        when(postRepository.findUsers()).thenReturn(userIds);

        postService.sendUserRequestsFromPost();
        userQueueService.sendUserRequestForAllItems(userIds);

        verify(postRepository).findUsers();
    }
}
