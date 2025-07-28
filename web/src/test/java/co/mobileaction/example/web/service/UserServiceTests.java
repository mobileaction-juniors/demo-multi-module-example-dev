package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author sa
 * @date 17.05.2021
 * @time 18:05
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTests
{
    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void saveUser_ShouldSaveUser()
    {
        User user = User.builder().id(1L).name("John").username("john").build();

        userService.saveUser(user);

        verify(userRepository).save(user);
    }

    @Test
    void findUsers_ShouldReturnPagedUsers()
    {
        Pageable pageable = PageRequest.of(0, 10);
        List<User> users = Arrays.asList(
                User.builder().id(1L).name("John").username("john").build(),
                User.builder().id(2L).name("Jane").username("jane").build()
        );
        Page<User> page = new PageImpl<>(users);
        when(userRepository.findAll(pageable)).thenReturn(page);

        List<User> result = userService.findUsers(pageable);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("John");
        assertThat(result.get(1).getName()).isEqualTo("Jane");
        verify(userRepository).findAll(pageable);
    }

    @Test
    void deleteUser_ShouldDeleteUser()
    {
        Long userId = 1L;

        userService.deleteUser(userId);

        verify(userRepository).deleteById(userId);
    }
}