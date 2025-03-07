package co.mobileaction.example.worker.client;

import co.mobileaction.example.common.dto.PostDto;
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
    public static final String API_POST_URL = "https://jsonplaceholder.typicode.com/posts/%s";

    private final IHttpRequestExecutor httpRequestExecutor;

    @Override
    public PostDto fetchPost(Long postId)
    {
        String url = String.format(API_POST_URL, postId);

        return httpRequestExecutor.executeGetRequest(url, PostDto.class);
    }
}
