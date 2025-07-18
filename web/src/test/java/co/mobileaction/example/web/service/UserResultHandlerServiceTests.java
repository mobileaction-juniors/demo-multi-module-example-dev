package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserCrawlRequestDto;
import co.mobileaction.example.web.model.User;
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
 * Unit tests for UserResultHandlerService
 */
@ExtendWith(MockitoExtension.class)
public class UserResultHandlerServiceTests {

    @Mock
    private IUserService userService;

    @InjectMocks
    private UserResultHandlerService userResultHandlerService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    private UserCrawlRequestDto testUserDto;

    @BeforeEach
    void setUp() {
        testUserDto = UserCrawlRequestDto.builder()
                .id(1L)
                .name("John Doe")
                .username("johndoe")
                .build();
    }

    @Test
    void executeMessage_ShouldSaveUserSuccessfully() {
        // When
        userResultHandlerService.executeMessage(testUserDto);

        // Then
        verify(userService).saveUser(userArgumentCaptor.capture());
        
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser.getId()).isEqualTo(1L);
        assertThat(capturedUser.getName()).isEqualTo("John Doe");
        assertThat(capturedUser.getUsername()).isEqualTo("johndoe");
    }

    @Test
    void executeMessage_ShouldHandleUserWithNullFields() {
        // Given
        UserCrawlRequestDto userDtoWithNulls = UserCrawlRequestDto.builder()
                .id(2L)
                .name(null)
                .username(null)
                .build();

        // When
        userResultHandlerService.executeMessage(userDtoWithNulls);

        // Then
        verify(userService).saveUser(userArgumentCaptor.capture());
        
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser.getId()).isEqualTo(2L);
        assertThat(capturedUser.getName()).isNull();
        assertThat(capturedUser.getUsername()).isNull();
    }

    @Test
    void convertFrom_ShouldConvertUserCrawlRequestDtoToUser() {
        // When
        User result = userResultHandlerService.convertFrom(testUserDto);

        // Then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("John Doe");
        assertThat(result.getUsername()).isEqualTo("johndoe");
    }

    @Test
    void convertFrom_ShouldHandleEmptyStrings() {
        // Given
        UserCrawlRequestDto userDtoWithEmptyStrings = UserCrawlRequestDto.builder()
                .id(3L)
                .name("")
                .username("")
                .build();

        // When
        User result = userResultHandlerService.convertFrom(userDtoWithEmptyStrings);

        // Then
        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getName()).isEqualTo("");
        assertThat(result.getUsername()).isEqualTo("");
    }

    @Test
    void convertFrom_ShouldHandleSpecialCharacters() {
        // Given
        UserCrawlRequestDto userDtoWithSpecialChars = UserCrawlRequestDto.builder()
                .id(4L)
                .name("José María")
                .username("user@123")
                .build();

        // When
        User result = userResultHandlerService.convertFrom(userDtoWithSpecialChars);

        // Then
        assertThat(result.getId()).isEqualTo(4L);
        assertThat(result.getName()).isEqualTo("José María");
        assertThat(result.getUsername()).isEqualTo("user@123");
    }

    @Test
    void convertFrom_ShouldHandleLongValues() {
        // Given
        UserCrawlRequestDto userDtoWithLongValues = UserCrawlRequestDto.builder()
                .id(999999999L)
                .name("Very Long Name That Exceeds Normal Length Expectations")
                .username("very_long_username_that_might_exceed_normal_length_expectations")
                .build();

        // When
        User result = userResultHandlerService.convertFrom(userDtoWithLongValues);

        // Then
        assertThat(result.getId()).isEqualTo(999999999L);
        assertThat(result.getName()).isEqualTo("Very Long Name That Exceeds Normal Length Expectations");
        assertThat(result.getUsername()).isEqualTo("very_long_username_that_might_exceed_normal_length_expectations");
    }
} 