package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for PostRequestHandlerService
 */
@ExtendWith(MockitoExtension.class)
public class PostRequestHandlerServiceTests {

    @InjectMocks
    private PostRequestHandlerService requestHandlerService;

    @Mock
    private ICrawlerClient crawlerClient;

    @Mock
    private AmqpTemplate resultQueueTemplate;

    @Captor
    private ArgumentCaptor<PostDto> postResultCaptor;

    private PostDto testPost;
    private QueueRequestDto testRequest;

    @BeforeEach
    void setUp() {
        testPost = PostDto.builder()
                .userId(1L)
                .id(1L)
                .body("body-1")
                .title("title-1")
                .build();

        testRequest = new QueueRequestDto(1L);
    }

    @Test
    void executeMessage_ShouldFetchPostAndSendToResultQueue() {
        // Given
        when(crawlerClient.fetchPost(eq(1L))).thenReturn(testPost);

        // When
        requestHandlerService.executeMessage(testRequest);

        // Then
        verify(crawlerClient).fetchPost(1L);
        verify(resultQueueTemplate).convertAndSend(postResultCaptor.capture());
        
        PostDto capturedResult = postResultCaptor.getValue();
        assertThat(capturedResult.getId()).isEqualTo(1L);
        assertThat(capturedResult.getUserId()).isEqualTo(1L);
        assertThat(capturedResult.getTitle()).isEqualTo("title-1");
        assertThat(capturedResult.getBody()).isEqualTo("body-1");
    }

    @Test
    void executeMessage_ShouldHandlePostWithNullFields() {
        // Given
        PostDto postWithNulls = PostDto.builder()
                .userId(2L)
                .id(2L)
                .body(null)
                .title(null)
                .build();

        QueueRequestDto requestWithNulls = new QueueRequestDto(2L);
        
        when(crawlerClient.fetchPost(eq(2L))).thenReturn(postWithNulls);

        // When
        requestHandlerService.executeMessage(requestWithNulls);

        // Then
        verify(crawlerClient).fetchPost(2L);
        verify(resultQueueTemplate).convertAndSend(postResultCaptor.capture());
        
        PostDto capturedResult = postResultCaptor.getValue();
        assertThat(capturedResult.getId()).isEqualTo(2L);
        assertThat(capturedResult.getUserId()).isEqualTo(2L);
        assertThat(capturedResult.getTitle()).isNull();
        assertThat(capturedResult.getBody()).isNull();
    }

    @Test
    void executeMessage_ShouldHandlePostWithEmptyStrings() {
        // Given
        PostDto postWithEmptyStrings = PostDto.builder()
                .userId(3L)
                .id(3L)
                .body("")
                .title("")
                .build();

        QueueRequestDto requestWithEmptyStrings = new QueueRequestDto(3L);
        
        when(crawlerClient.fetchPost(eq(3L))).thenReturn(postWithEmptyStrings);

        // When
        requestHandlerService.executeMessage(requestWithEmptyStrings);

        // Then
        verify(crawlerClient).fetchPost(3L);
        verify(resultQueueTemplate).convertAndSend(postResultCaptor.capture());
        
        PostDto capturedResult = postResultCaptor.getValue();
        assertThat(capturedResult.getId()).isEqualTo(3L);
        assertThat(capturedResult.getUserId()).isEqualTo(3L);
        assertThat(capturedResult.getTitle()).isEqualTo("");
        assertThat(capturedResult.getBody()).isEqualTo("");
    }

    @Test
    void executeMessage_ShouldHandleLargePostId() {
        // Given
        PostDto postWithLargeId = PostDto.builder()
                .userId(999999999L)
                .id(999999999L)
                .body("Large ID Post Body")
                .title("Large ID Post Title")
                .build();

        QueueRequestDto requestWithLargeId = new QueueRequestDto(999999999L);
        
        when(crawlerClient.fetchPost(eq(999999999L))).thenReturn(postWithLargeId);

        // When
        requestHandlerService.executeMessage(requestWithLargeId);

        // Then
        verify(crawlerClient).fetchPost(999999999L);
        verify(resultQueueTemplate).convertAndSend(postResultCaptor.capture());
        
        PostDto capturedResult = postResultCaptor.getValue();
        assertThat(capturedResult.getId()).isEqualTo(999999999L);
        assertThat(capturedResult.getUserId()).isEqualTo(999999999L);
        assertThat(capturedResult.getTitle()).isEqualTo("Large ID Post Title");
        assertThat(capturedResult.getBody()).isEqualTo("Large ID Post Body");
    }

    @Test
    void executeMessage_ShouldHandleSpecialCharactersInPostData() {
        // Given
        PostDto postWithSpecialChars = PostDto.builder()
                .userId(4L)
                .id(4L)
                .body("Post body with special chars: áéíóú ñ")
                .title("Post title with special chars: áéíóú ñ")
                .build();

        QueueRequestDto requestWithSpecialChars = new QueueRequestDto(4L);
        
        when(crawlerClient.fetchPost(eq(4L))).thenReturn(postWithSpecialChars);

        // When
        requestHandlerService.executeMessage(requestWithSpecialChars);

        // Then
        verify(crawlerClient).fetchPost(4L);
        verify(resultQueueTemplate).convertAndSend(postResultCaptor.capture());
        
        PostDto capturedResult = postResultCaptor.getValue();
        assertThat(capturedResult.getId()).isEqualTo(4L);
        assertThat(capturedResult.getUserId()).isEqualTo(4L);
        assertThat(capturedResult.getTitle()).isEqualTo("Post title with special chars: áéíóú ñ");
        assertThat(capturedResult.getBody()).isEqualTo("Post body with special chars: áéíóú ñ");
    }

    @Test
    void executeMessage_ShouldHandleLongPostContent() {
        // Given
        String longTitle = "Very long post title that exceeds normal length expectations and might cause issues if not handled properly";
        String longBody = "Very long post body that exceeds normal length expectations and might cause issues if not handled properly. " +
                "This is a test to ensure that the service can handle posts with very long content without any problems.";
        
        PostDto postWithLongContent = PostDto.builder()
                .userId(5L)
                .id(5L)
                .body(longBody)
                .title(longTitle)
                .build();

        QueueRequestDto requestWithLongContent = new QueueRequestDto(5L);
        
        when(crawlerClient.fetchPost(eq(5L))).thenReturn(postWithLongContent);

        // When
        requestHandlerService.executeMessage(requestWithLongContent);

        // Then
        verify(crawlerClient).fetchPost(5L);
        verify(resultQueueTemplate).convertAndSend(postResultCaptor.capture());
        
        PostDto capturedResult = postResultCaptor.getValue();
        assertThat(capturedResult.getId()).isEqualTo(5L);
        assertThat(capturedResult.getUserId()).isEqualTo(5L);
        assertThat(capturedResult.getTitle()).isEqualTo(longTitle);
        assertThat(capturedResult.getBody()).isEqualTo(longBody);
    }
}
