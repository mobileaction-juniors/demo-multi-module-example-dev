package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author sa
 * @date 18.05.2021
 * @time 10:37
 */
@ExtendWith(MockitoExtension.class)
public class PostRequestHandlerServiceTests
{
    @InjectMocks
    private PostRequestHandlerService requestHandlerService;

    @Mock
    private ICrawlerClient crawlerClient;

    @Mock
    private AmqpTemplate resultQueueTemplate;

    @Test
    public void test_crawlAppForAllCountries_missingApp()
    {
        PostDto post = PostDto.builder()
                .userId(1L)
                .id(1L)
                .body("body-1")
                .title("title-1")
                .build();

        when(crawlerClient.fetchPost(eq(1L))).thenReturn(post);

        QueueRequestDto dto = new QueueRequestDto(1L);


        requestHandlerService.executeMessage(dto);

        verify(crawlerClient).fetchPost(1L);
        verify(resultQueueTemplate).convertAndSend(post);
    }
}
