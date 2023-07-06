package co.mobileaction.example.web.service;

import co.mobileaction.example.web.exception.AlreadyExistException;
import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 * @author sa
 * @date 17.05.2021
 * @time 19:19
 */
@DataJpaTest
@Sql("/data/users.sql")
public class UserServiceTests
{
    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void saveUser() throws AlreadyExistException
    {
        User user = User.builder()
                .id(5L)
                .name("name-5")
                .username("username-5")
                .email("email-5")
                .build();

        userService.saveUser(user);

        List<User> list = userRepository.findAll();
        assertThat(list).hasSize(4);
    }

    @Test
    public void saveUser_UserAlreadyExist_ThrowException() throws AlreadyExistException
    {
        User user = User.builder()
                .id(1L)
                .name("name-1")
                .username("username-1")
                .email("email-1")
                .build();

        doThrow(AlreadyExistException.class).when(mock(IUserService.class)).saveUser(user);
        assertThrows(AlreadyExistException.class, () -> userService.saveUser(user));

        List<User> list = userRepository.findAll();
        assertThat(list).hasSize(3);
    }
}
