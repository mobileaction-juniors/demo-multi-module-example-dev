package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.web.model.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * @author sa
 * @date 17.05.2021
 * @time 19:26
 */
@ExtendWith(MockitoExtension.class)
public class PostResultHandlerServiceTests
{
    @InjectMocks
    private PostResultHandlerService postResultHandlerService;

    @Mock
    private IPostService postService;

    @Captor
    private ArgumentCaptor<Post> postArgumentCaptor;

    @Test
    public void savePost()
    {
        PostDto post = PostDto.builder()
                .userId(5L)
                .id(5L)
                .body("body-5")
                .title("title-5")
                .build();

        postResultHandlerService.executeMessage(post);

        verify(postService).savePost(postArgumentCaptor.capture());
        assertThat(postArgumentCaptor.getValue().getId()).isEqualTo(5L);
    }
}
