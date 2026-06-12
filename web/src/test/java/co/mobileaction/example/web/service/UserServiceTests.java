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
 * @author serkankorkut
 * @date 12.06.2026
 * @time 15:06
 */
@DataJpaTest
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
        List<Post> initialList = postRepository.findAllByUserId(1L);
        assertThat(initialList).hasSizeGreaterThan(0);

        userService.deleteAllPostsOfUser(1L);

        List<Post> list = postRepository.findAllByUserId(1L);
        assertThat(list).hasSize(0);
    }
}
