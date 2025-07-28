package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.repository.IPostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author sa
 * @date 17.05.2021
 * @time 19:19
 */
@ExtendWith(MockitoExtension.class)
public class PostServiceTests
{
    @Mock
    private IPostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    public void findPosts()
    {
        var page = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "id"));
        List<Post> posts = Arrays.asList(
                Post.builder().id(1L).userId(1L).title("Title 1").body("Body 1").build(),
                Post.builder().id(2L).userId(1L).title("Title 2").body("Body 2").build(),
                Post.builder().id(3L).userId(2L).title("Title 3").body("Body 3").build()
        );
        Page<Post> postPage = new PageImpl<>(posts);
        
        when(postRepository.findAll(page)).thenReturn(postPage);

        List<Post> result = postService.findPosts(page);

        assertThat(result).hasSize(3);
        assertThat(result).extracting(Post::getId).containsExactlyInAnyOrder(1L, 2L, 3L);
        verify(postRepository).findAll(page);
    }

    @Test
    public void findAllPostsOfUser()
    {
        List<Post> userPosts = Arrays.asList(
                Post.builder().id(1L).userId(1L).title("Title 1").body("Body 1").build(),
                Post.builder().id(2L).userId(1L).title("Title 2").body("Body 2").build()
        );
        
        when(postRepository.findAllByUserId(1L)).thenReturn(userPosts);

        List<Post> result = postService.findAllPostsOfUser(1L);

        assertThat(result).hasSize(2);
        assertThat(result).extracting(Post::getId).containsExactlyInAnyOrder(1L, 2L);
        verify(postRepository).findAllByUserId(1L);
    }

    @Test
    public void savePost()
    {
        Post post = Post.builder()
                .userId(5L)
                .id(5L)
                .body("body-5")
                .title("title-5")
                .build();

        postService.savePost(post);

        verify(postRepository).save(post);
    }

    @Test
    public void deletePost()
    {
        postService.deletePost(1L);

        verify(postRepository).deleteById(1L);
    }

    @Test
    public void findDistinctUserIds()
    {
        List<Long> userIds = Arrays.asList(1L, 2L, 3L);
        when(postRepository.findDistinctUserIds()).thenReturn(userIds);

        List<Long> result = postService.findDistinctUserIds();

        assertThat(result).hasSize(3);
        assertThat(result).containsExactlyInAnyOrder(1L, 2L, 3L);
        verify(postRepository).findDistinctUserIds();
    }
}
