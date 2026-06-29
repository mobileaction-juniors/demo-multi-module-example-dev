package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author berkturk
 * @date 23.06.2026
 * @time 10:58
 */
@SpringBootTest
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

        var page = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "id"));

        List<Post> listOfOtherUsers = postService.findPosts(page);

        assertThat(listOfOtherUsers).hasSize(2);
        assertThat(listOfOtherUsers).extracting(x -> x.getUserId()).containsExactlyInAnyOrder(2L, 3L);
    }
}
