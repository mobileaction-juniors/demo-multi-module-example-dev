package co.mobileaction.example.worker.client;

import co.mobileaction.example.common.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCrawlerClient implements IUserCrawlerClient
{
    public static final String API_USER_URL = "https://jsonplaceholder.typicode.com/users/%s";

    private final IHttpRequestExecutor httpRequestExecutor;

    @Override
    public UserDto fetchUser(Long userId)
    {
        String url = String.format(API_USER_URL, userId);

        return httpRequestExecutor.executeGetRequest(url, UserDto.class);
    }
}
