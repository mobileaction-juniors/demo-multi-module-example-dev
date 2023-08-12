package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql("/data/users.sql")
public class UserServiceTests
{
    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void findUsers()
    {
        userService.findUsers();
        List<User> users = userRepository.findAll();

        assertThat(users).hasSize(4);
        assertThat(users).extracting(x -> x.getId()).containsExactlyInAnyOrder(1L, 2L, 3L, 4L);
    }
}
