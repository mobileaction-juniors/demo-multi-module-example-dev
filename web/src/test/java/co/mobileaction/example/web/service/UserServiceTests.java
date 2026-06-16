package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.exception.UserFoundException;
import co.mobileaction.example.web.exception.UserNotFoundException;
import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTests
{
    private static final Pageable PAGEABLE = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserRepository userRepository;

    @Test
    void findUsers_whenUsersExist_returnsUserDtos()
    {
        User user1 = new User(1L, "name-1", "username-1", "email-1");
        User user2 = new User(2L, "name-2", "username-2", "email-2");

        when(userRepository.findAll(PAGEABLE)).thenReturn(new PageImpl<>(List.of(user1, user2)));

        List<UserDto> result = userService.findUsers(PAGEABLE);

        assertThat(result).extracting(UserDto::name).containsExactly("name-1", "name-2");
    }

    @Test
    void findUsers_whenNoUsers_throwsUserNotFoundException()
    {
        when(userRepository.findAll(PAGEABLE)).thenReturn(new PageImpl<>(List.of()));

        assertThatThrownBy(() -> userService.findUsers(PAGEABLE)).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void saveUser_whenUserIsUnique_savesUser()
    {
        User user = new User(null, "name-1", "username-1", "email-1");

        userService.saveUser(user);

        verify(userRepository).save(user);
    }

    @Test
    void saveUser_whenUsernameExists_throwsUserFoundException()
    {
        User user = new User(null, "name-1", "username-1", "email-1");

        when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);

        assertThatThrownBy(() -> userService.saveUser(user)).isInstanceOf(UserFoundException.class);

        verify(userRepository, never()).save(user);
    }

    @Test
    void saveUser_whenEmailExists_throwsUserFoundException()
    {
        User user = new User(null, "name-1", "username-1", "email-1");

        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        assertThatThrownBy(() -> userService.saveUser(user)).isInstanceOf(UserFoundException.class);

        verify(userRepository, never()).save(user);
    }
}