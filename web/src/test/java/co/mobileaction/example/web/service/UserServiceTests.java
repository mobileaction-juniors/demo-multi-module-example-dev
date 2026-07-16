package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
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
                .id(1L)
                .name("name-1")
                .username("username-1")
                .build();

        userService.saveUser(user);

        assertThat(userRepository.findById(1L))
                .hasValueSatisfying(savedUser -> {
                    assertThat(savedUser.getName()).isEqualTo("name-1");
                    assertThat(savedUser.getUsername()).isEqualTo("username-1");
                });
    }
}
