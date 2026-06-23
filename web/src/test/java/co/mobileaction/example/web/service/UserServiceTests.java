package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author berkturk
 * @date 22.06.2026
 * @time 17:24
 */
@DataJpaTest
@Sql("/data/user_details.sql")
public class UserServiceTests
{
    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void saveUser()
    {
        User user = User.builder()
                .id(5L)
                .name("name-5")
                .username("username-5")
                .email("email-5")
                .phone("phone-5")
                .website("website-5")
                .build();

        userService.saveUser(user);

        List<User> list = userRepository.findAll();

        assertThat(list).hasSize(5);
    }
}
