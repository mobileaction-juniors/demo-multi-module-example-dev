package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.model.dto.CreateUserCmd;
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
@Sql(value = "/data/users.sql")
public class UserServiceTests {
    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void findUsers()
    {
        var page = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "id"));

        List<User> list = userService.findUsers(page);

        assertThat(list).hasSize(3);
        assertThat(list).extracting(User::getId).containsExactlyInAnyOrder(1L, 2L, 3L);
    }

    @Test
    public void saveUser()
    {
        CreateUserCmd createUserCmd = new CreateUserCmd(
                "sezer5",
                "sezer5",
                "sezer5@sezer.com"
        );

        userService.saveUser(createUserCmd);

        List<User> list = userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        assertThat(list).hasSize(5);
    }
}
