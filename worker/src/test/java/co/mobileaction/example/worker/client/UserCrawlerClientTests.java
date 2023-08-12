package co.mobileaction.example.worker.client;

import co.mobileaction.example.common.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserCrawlerClientTests
{
    @InjectMocks
    private UserCrawlerClient userCrawlerClient;

    @Mock
    private IHttpRequestExecutor httpRequestExecutor;

    @Test
    public void test_fetchUser()
    {
        String api_url = "https://jsonplaceholder.typicode.com/users/%s";
        Long userId = 1L;
        String url = String.format(api_url, userId);

        UserDto user = UserDto.builder()
                .id(userId)
                .name("name1")
                .username("username1")
                .email("mail1")
                .build();

        when(httpRequestExecutor.executeGetRequest(url, UserDto.class)).thenReturn(user);

        UserDto userDto = userCrawlerClient.fetchUser(userId);

        verify(httpRequestExecutor).executeGetRequest(url, UserDto.class);
        assertEquals(userDto.getId(), 1L);
    }
}
