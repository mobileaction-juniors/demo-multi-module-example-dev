package co.mobileaction.example.worker.client;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:00
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlerClient implements ICrawlerClient
{
    public static final String API_URL = "https://jsonplaceholder.typicode.com";

    private final IHttpRequestExecutor httpRequestExecutor;

    @Override
    public PostDto fetchPost(Long postId)
    {
        String url = String.format(API_URL + "/posts/%s", postId);

        return httpRequestExecutor.executeGetRequest(url, PostDto.class);
    }

    @Override
    public UserDto fetchUser(Long userId)
    {
        String url = String.format(API_URL + "/users/%s", userId);

        return httpRequestExecutor.executeGetRequest(url, UserDto.class);
    }
}
