package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.repository.IPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceMockTests
{
    @Mock
    private IPostRepository postRepository;

    private PostService postService;

    private final Long postId = 1L;

    @BeforeEach
    public void setUp()
    {
        postService = new PostService(postRepository);
    }

    @Test
    public void findPosts()
    {
        var page = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "id"));

        List<Post> postsList = Arrays.asList(
                new Post(1L, 1L, "1", "1"),
                new Post(2L, 1L, "2", "2"),
                new Post(3L, 1L, "3", "3")
        );

        Page<Post> postsPage = new PageImpl<>(postsList);

        when(postRepository.findAll(page)).thenReturn(postsPage);

        postService.findPosts(page);

        verify(postRepository).findAll(page);
    }

    @Test
    public void deletePost()
    {
        postService.deletePost(postId);

        verify(postRepository).deleteById(postId);
    }

    @Test
    public void deletePostsByUserId()
    {
        postService.deletePostsByUserId(postId);

        verify(postRepository).deletePostsByUserId(postId);
    }
}
