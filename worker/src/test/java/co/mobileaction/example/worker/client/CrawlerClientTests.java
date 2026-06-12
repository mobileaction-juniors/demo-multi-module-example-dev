package co.mobileaction.example.worker.client;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
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

        PostDto post = new PostDto(postId, 1L, "title-1", "body-1");

        when(httpRequestExecutor.executeGetRequest(url, PostDto.class)).thenReturn(post);

        PostDto postDto = crawlerClient.fetchPost(postId);

        assertEquals(postDto.id(), 1L);
    }

    @Test
    public void test_fetchUser()
    {
        String api_url = "https://jsonplaceholder.typicode.com/users/%s";
        Long userId = 1L;
        String url = String.format(api_url, userId);

        UserDto user = new UserDto("name-1", "username-1", "email-1");

        when(httpRequestExecutor.executeGetRequest(url, UserDto.class)).thenReturn(user);

        UserDto userDto = crawlerClient.fetchUser(userId);

        assertEquals(userDto.username(), "username-1");
    }
}
