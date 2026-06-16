package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.repository.IPostRepository;
import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(UserService.class)
@Sql("/data/posts.sql")
public class UserServiceTests
{
    @Autowired
    private IUserService userService;

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void deleteAllPostsOfUser()
    {
        userService.deleteAllPostsOfUser(1L);

        List<Post> list = postRepository.findAll();

        assertThat(list).hasSize(2);
    }
    @Test
    public void saveUser()
    {
        co.mobileaction.example.web.model.User user = co.mobileaction.example.web.model.User.builder()
                .id(1L)
                .name("Leanne Graham")
                .username("Bret")
                .email("Sincere@april.biz")
                .build();

        userService.saveUser(user);

        List<co.mobileaction.example.web.model.User> list = userRepository.findAll();

        assertThat(list).hasSize(1);
        assertThat(list.get(0).getName()).isEqualTo("Leanne Graham");
    }
}
