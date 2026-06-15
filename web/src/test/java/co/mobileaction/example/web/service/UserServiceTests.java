package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.repository.IPostRepository;
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

    @Test
    public void deleteAllPostsOfUser()
    {
        userService.deleteAllPostsOfUser(1L);

        List<Post> list = postRepository.findAll();

        assertThat(list).hasSize(2);
    }
}
