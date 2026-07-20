package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.LocalUser;
import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql("/data/users.sql")
class UserServiceTest
{
    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRepository userRepository;

    @Test
    void saveUser()
    {
        LocalUser user = LocalUser.builder()
                .id(5L)
                .username("user5")
                .email("user5@gmail.com")
                .phone("5000000005")
                .build();

        userService.saveUser(user);

        List<LocalUser> users = userRepository.findAll();
        assertThat(users).hasSize(5);
    }

    @Test
    void findUsers()
    {
        var page = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "id"));

        List<LocalUser> users = userService.findUsers(page);

        assertThat(users).hasSize(3);
        assertThat(users).extracting(LocalUser::getId).containsExactlyInAnyOrder(1L, 2L, 3L);
    }
}
