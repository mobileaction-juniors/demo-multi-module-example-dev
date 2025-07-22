package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.web.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for PostResultHandlerService
 */
@ExtendWith(MockitoExtension.class)
public class PostResultHandlerServiceTests {

    private static final long TEST_POST_ID = 5L;
    private static final long TEST_POST_USER_ID = 5L;
    private static final String TEST_POST_TITLE = "title-5";
    private static final String TEST_POST_BODY = "body-5";

    @InjectMocks
    private PostResultHandlerService postResultHandlerService;

    @Mock
    private IPostService postService;

    @Captor
    private ArgumentCaptor<Post> postArgumentCaptor;

    private PostDto testPostDto;

    @BeforeEach
    void setUp() {
        testPostDto = PostDto.builder()
                .userId(TEST_POST_USER_ID)
                .id(TEST_POST_ID)
                .body(TEST_POST_BODY)
                .title(TEST_POST_TITLE)
                .build();
    }

    @Test
    void executeMessage_ShouldSavePostSuccessfully() {
        // When
        postResultHandlerService.executeMessage(testPostDto);

        // Then
        verify(postService).savePost(postArgumentCaptor.capture());
        
        Post capturedPost = postArgumentCaptor.getValue();
        assertThat(capturedPost.getId()).isEqualTo(TEST_POST_ID);
        assertThat(capturedPost.getUserId()).isEqualTo(TEST_POST_USER_ID);
        assertThat(capturedPost.getTitle()).isEqualTo(TEST_POST_TITLE);
        assertThat(capturedPost.getBody()).isEqualTo(TEST_POST_BODY);
    }

    @Test
    void executeMessage_ShouldHandlePostWithNullFields() {
        // Given
        PostDto postDtoWithNulls = PostDto.builder()
                .userId(2L)
                .id(2L)
                .body(null)
                .title(null)
                .build();

        // When
        postResultHandlerService.executeMessage(postDtoWithNulls);

        // Then
        verify(postService).savePost(postArgumentCaptor.capture());
        
        Post capturedPost = postArgumentCaptor.getValue();
        assertThat(capturedPost.getId()).isEqualTo(2L);
        assertThat(capturedPost.getUserId()).isEqualTo(2L);
        assertThat(capturedPost.getTitle()).isNull();
        assertThat(capturedPost.getBody()).isNull();
    }

    @Test
    void executeMessage_ShouldHandlePostWithEmptyStrings() {
        // Given
        PostDto postDtoWithEmptyStrings = PostDto.builder()
                .userId(3L)
                .id(3L)
                .body("")
                .title("")
                .build();

        // When
        postResultHandlerService.executeMessage(postDtoWithEmptyStrings);

        // Then
        verify(postService).savePost(postArgumentCaptor.capture());
        
        Post capturedPost = postArgumentCaptor.getValue();
        assertThat(capturedPost.getId()).isEqualTo(3L);
        assertThat(capturedPost.getUserId()).isEqualTo(3L);
        assertThat(capturedPost.getTitle()).isEqualTo("");
        assertThat(capturedPost.getBody()).isEqualTo("");
    }

    @Test
    void executeMessage_ShouldHandleLargePostId() {
        // Given
        PostDto postDtoWithLargeId = PostDto.builder()
                .userId(999999999L)
                .id(999999999L)
                .body("Large ID Post Body")
                .title("Large ID Post Title")
                .build();

        // When
        postResultHandlerService.executeMessage(postDtoWithLargeId);

        // Then
        verify(postService).savePost(postArgumentCaptor.capture());
        
        Post capturedPost = postArgumentCaptor.getValue();
        assertThat(capturedPost.getId()).isEqualTo(999999999L);
        assertThat(capturedPost.getUserId()).isEqualTo(999999999L);
        assertThat(capturedPost.getTitle()).isEqualTo("Large ID Post Title");
        assertThat(capturedPost.getBody()).isEqualTo("Large ID Post Body");
    }

    @Test
    void executeMessage_ShouldHandleSpecialCharactersInPostData() {
        // Given
        PostDto postDtoWithSpecialChars = PostDto.builder()
                .userId(4L)
                .id(4L)
                .body("Post body with special chars: áéíóú ñ")
                .title("Post title with special chars: áéíóú ñ")
                .build();

        // When
        postResultHandlerService.executeMessage(postDtoWithSpecialChars);

        // Then
        verify(postService).savePost(postArgumentCaptor.capture());
        
        Post capturedPost = postArgumentCaptor.getValue();
        assertThat(capturedPost.getId()).isEqualTo(4L);
        assertThat(capturedPost.getUserId()).isEqualTo(4L);
        assertThat(capturedPost.getTitle()).isEqualTo("Post title with special chars: áéíóú ñ");
        assertThat(capturedPost.getBody()).isEqualTo("Post body with special chars: áéíóú ñ");
    }

    @Test
    void executeMessage_ShouldHandleLongPostContent() {
        // Given
        String longTitle = "Very long post title that exceeds normal length expectations and might cause issues if not handled properly";
        String longBody = "Very long post body that exceeds normal length expectations and might cause issues if not handled properly. " +
                "This is a test to ensure that the service can handle posts with very long content without any problems.";
        
        PostDto postDtoWithLongContent = PostDto.builder()
                .userId(6L)
                .id(6L)
                .body(longBody)
                .title(longTitle)
                .build();

        // When
        postResultHandlerService.executeMessage(postDtoWithLongContent);

        // Then
        verify(postService).savePost(postArgumentCaptor.capture());
        
        Post capturedPost = postArgumentCaptor.getValue();
        assertThat(capturedPost.getId()).isEqualTo(6L);
        assertThat(capturedPost.getUserId()).isEqualTo(6L);
        assertThat(capturedPost.getTitle()).isEqualTo(longTitle);
        assertThat(capturedPost.getBody()).isEqualTo(longBody);
    }
}
