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
    }

    @Test
    void executeMessage_ShouldHandleUserWithNullFields() {
        // Given
        UserCrawlRequestDto userDtoWithNulls = UserCrawlRequestDto.builder()
                .id(2L)
                .build();

        // When
        userResultHandlerService.executeMessage(userDtoWithNulls);

        // Then
        verify(userService).saveUser(userArgumentCaptor.capture());
        
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser.getId()).isEqualTo(2L);
    }

    @Test
    void userFrom_ShouldConvertUserCrawlRequestDtoToUser() {
        // When
        User result = User.from(testUserDto);

        // Then
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void userFrom_ShouldHandleEmptyStrings() {
        // Given
        UserCrawlRequestDto userDtoWithEmptyStrings = UserCrawlRequestDto.builder()
                .id(3L)
                .build();

        // When
        User result = User.from(userDtoWithEmptyStrings);

        // Then
        assertThat(result.getId()).isEqualTo(3L);
    }

    @Test
    void userFrom_ShouldHandleSpecialCharacters() {
        // Given
        UserCrawlRequestDto userDtoWithSpecialChars = UserCrawlRequestDto.builder()
                .id(4L)
                .build();

        // When
        User result = User.from(userDtoWithSpecialChars);

        // Then
        assertThat(result.getId()).isEqualTo(4L);
    }

    @Test
    void userFrom_ShouldHandleLongValues() {
        // Given
        UserCrawlRequestDto userDtoWithLongValues = UserCrawlRequestDto.builder()
                .id(999999999999L)
                .build();

        // When
        User result = User.from(userDtoWithLongValues);

        // Then
        assertThat(result.getId()).isEqualTo(999999999999L);
    }
} 