package co.mobileaction.example.web.service;

import co.mobileaction.example.web.repository.IPostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private IPostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void deleteAllPostsOfUser_callsRepository() 
    {
        Long userId = 1L;

        postService.deleteAllPostsOfUser(userId);

        verify(postRepository).deleteByUserId(userId);
    }
}
