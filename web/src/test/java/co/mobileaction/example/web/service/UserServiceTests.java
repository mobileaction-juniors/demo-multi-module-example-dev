package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for UserService
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(1L)
                .name("John Doe")
                .username("johndoe")
                .build();
    }

    @Test
    void saveUser_ShouldSaveUserSuccessfully() {
        // Given
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // When
        userService.saveUser(testUser);

        // Then
        verify(userRepository).save(testUser);
    }

    @Test
    void findAll_ShouldReturnAllUsers() {
        // Given
        List<User> expectedUsers = Arrays.asList(
                User.builder().id(1L).name("John Doe").username("johndoe").build(),
                User.builder().id(2L).name("Jane Smith").username("janesmith").build()
        );
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // When
        List<User> actualUsers = userService.findAll();

        // Then
        assertThat(actualUsers).hasSize(2);
        assertThat(actualUsers).isEqualTo(expectedUsers);
        verify(userRepository).findAll();
    }

    @Test
    void findAll_ShouldReturnEmptyList_WhenNoUsersExist() {
        // Given
        when(userRepository.findAll()).thenReturn(Arrays.asList());

        // When
        List<User> actualUsers = userService.findAll();

        // Then
        assertThat(actualUsers).isEmpty();
        verify(userRepository).findAll();
    }

    @Test
    void findById_ShouldReturnUser_WhenUserExists() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        // When
        User actualUser = userService.findById(1L);

        // Then
        assertThat(actualUser).isEqualTo(testUser);
        verify(userRepository).findById(1L);
    }

    @Test
    void findById_ShouldThrowRuntimeException_WhenUserNotFound() {
        // Given
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> userService.findById(999L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User not found");
        verify(userRepository).findById(999L);
    }
} 