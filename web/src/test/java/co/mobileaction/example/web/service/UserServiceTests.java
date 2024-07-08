package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IPostRepository;
import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Mehmet Akif Sahin
 * @date 08.07.2024
 * @time 10:09
 */
@DataJpaTest
@Sql("/data/posts.sql")
@Sql("/data/users.sql")
class UserServiceTests
{
    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPostRepository postRepository;

    @Test
    void deleteAllPosts()
    {
        Long mockUserId = 1L;

        postRepository.deleteAllByUserId(mockUserId);

        List<Post> posts = postRepository.findAll();

        assertThat(posts).hasSize(2);
    }

    @Test
    void findDistinctUserIds()
    {
        List<Long> distinctUserIds = postRepository.findDistinctUserId();

        assertThat(distinctUserIds).hasSize(3).containsExactlyInAnyOrder(1L, 2L, 3L);
    }

    @Test
    void saveUser()
    {
        User user = User.builder()
                .id(6L)
                .name("name-6")
                .username("username-6")
                .email("email-6")
                .build();

        userService.saveUser(user);

        List<User> users = userRepository.findAll();

        assertThat(users).hasSize(6);
    }

    @Test
    void findUsers()
    {
        PageRequest page = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        List<User> users = userService.findUsers(page);

        assertThat(users).hasSize(5).extracting(User::getId).containsExactlyInAnyOrder(1L ,2L ,3L ,4L ,5L);
    }
}
