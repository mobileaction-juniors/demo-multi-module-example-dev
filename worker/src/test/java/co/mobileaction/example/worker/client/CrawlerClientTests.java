package co.mobileaction.example.worker.client;

import co.mobileaction.example.common.dto.PostDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrawlerClientTests
{
    @InjectMocks
    private CrawlerClient crawlerClient;

    @Mock
    private IHttpRequestExecutor httpRequestExecutor;

    @Test
    public void test_fetchPost()
    {
        String api_url = "https://jsonplaceholder.typicode.com/posts/%s";
        Long postId = 1L;
        String url = String.format(api_url, postId);

        PostDto post = PostDto.builder()
                .userId(1L)
                .id(postId)
                .body("body-1")
                .title("title-1")
                .build();

        when(httpRequestExecutor.executeGetRequest(url, PostDto.class)).thenReturn(post);

        PostDto postDto = crawlerClient.fetchPost(postId);

        verify(httpRequestExecutor).executeGetRequest(url, PostDto.class);
        assertEquals(postDto.getId(), 1L);
    }
}
