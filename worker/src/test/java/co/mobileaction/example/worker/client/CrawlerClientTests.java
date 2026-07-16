package co.mobileaction.example.worker.client;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        PostDto post = PostDto.builder()
                .userId(1L)
                .id(postId)
                .body("body-1")
                .title("title-1")
                .build();

        when(httpRequestExecutor.executeGetRequest(url, PostDto.class)).thenReturn(post);

        PostDto postDto = crawlerClient.fetchPost(postId);

        assertEquals(postDto.getId(), 1L);
    }

    @Test
    public void test_fetchUser()
    {
        String api_url = "https://jsonplaceholder.typicode.com/users/%s";
        Long userId = 1L;
        String url = String.format(api_url, userId);

        UserDto user = UserDto.builder()
                .id(userId)
                .name("name-1")
                .username("username-1")
                .build();

        when(httpRequestExecutor.executeGetRequest(url, UserDto.class)).thenReturn(user);

        UserDto userDto = crawlerClient.fetchUser(userId);

        assertSame(user, userDto);
    }

    @Test
    public void test_deserializeUser_ignoresUnknownFields() throws Exception
    {
        String userJson = """
                {
                  "id": 1,
                  "name": "Leanne Graham",
                  "username": "Bret",
                  "email": "Sincere@april.biz",
                  "address": {
                    "street": "Kulas Light",
                    "suite": "Apt. 556",
                    "city": "Gwenborough",
                    "zipcode": "92998-3874",
                    "geo": {
                      "lat": "-37.3159",
                      "lng": "81.1496"
                    }
                  },
                  "phone": "1-770-736-8031 x56442",
                  "website": "hildegard.org",
                  "company": {
                    "name": "Romaguera-Crona",
                    "catchPhrase": "Multi-layered client-server neural-net",
                    "bs": "harness real-time e-markets"
                  }
                }
                """;
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

        UserDto user = objectMapper.readValue(userJson, UserDto.class);

        assertEquals(1L, user.getId());
        assertEquals("Leanne Graham", user.getName());
        assertEquals("Bret", user.getUsername());
    }
}
