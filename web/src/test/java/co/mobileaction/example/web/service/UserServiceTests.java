package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.repository.IPostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author berkturk
 * @date 23.06.2026
 * @time 10:58
 */
@DataJpaTest
@Sql("/data/posts.sql")
public class UserServiceTests
{
    @Autowired
    private IUserService userService;

    @Autowired
    private IPostService postService;

    @Test
    public void deleteAllPostsOfUser()
    {
        userService.deleteAllPostsOfUser(1L);

        List<Post> list = postService.findAllPostsOfUser(1L);

        assertThat(list).hasSize(0);
    }
}
