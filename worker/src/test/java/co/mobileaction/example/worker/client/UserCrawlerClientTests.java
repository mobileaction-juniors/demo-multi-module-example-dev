package co.mobileaction.example.worker.client;

import co.mobileaction.example.common.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserCrawlerClientTests
{
    @InjectMocks
    private CrawlerClient userCrawlerClient;

    @Mock
    private HttpRequestExecutor httpRequestExecutor;

    @Test
    public void test_fetchUser()
    {
        String user_api_url = "https://jsonplaceholder.typicode.com/users/%s";
        Long userId = 1L;
        String user_url = String.format(user_api_url,userId);

        UserDto user = UserDto.builder()
                .id(userId)
                .name("name-1")
                .username("username-1")
                .email("email-1")
                .build();

        when(httpRequestExecutor.executeGetRequest(user_url, UserDto.class)).thenReturn(user);

        UserDto userDto = userCrawlerClient.fetchUser(userId);

        assertEquals(userDto.getId(), userId);
    }
}
