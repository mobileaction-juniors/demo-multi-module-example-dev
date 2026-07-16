package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.repository.IPostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author sa
 * @date 17.05.2021
 * @time 19:19
 */
@DataJpaTest
@Sql("/data/posts.sql")
public class PostServiceTests
{
    @Autowired
    private IPostService postService;

    @Autowired
    private IPostRepository postRepository;

    @Test
    public void findPosts()
    {
        var page = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "id"));

        List<Post> list = postService.findPosts(page);

        assertThat(list).hasSize(3);
        assertThat(list).extracting(x -> x.getId()).containsExactlyInAnyOrder(1L, 2L, 3L);
    }

    @Test
    public void findAllPostsOfUser()
    {
        List<Post> list = postService.findAllPostsOfUser(1L);

        assertThat(list).hasSize(2);
        assertThat(list).extracting(x -> x.getId()).containsExactlyInAnyOrder(1L, 2L);
    }

    @Test
    public void savePost()
    {
        Post post = Post.builder()
                .userId(5L)
                .id(5L)
                .body("body-5")
                .title("title-5")
                .build();

        postService.savePost(post);

        List<Post> list = postRepository.findAll();

        assertThat(list).hasSize(5);
    }

    @Test
    public void deletePost()
    {
        postService.deletePost(1L);

        List<Post> list = postRepository.findAll();

        assertThat(list).hasSize(3);
    }

    @Test
    public void deleteAllPostOfUser()
    {
        Post post1 = new Post();
        post1.setId(-1L);
        post1.setBody("post1");
        post1.setUserId(-1L);
        post1.setTitle("Post1");
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setId(-2L);
        post2.setBody("post2");
        post2.setUserId(-1L);
        post2.setTitle("Post2");
        postRepository.save(post2);

        assertThat(postRepository.findAllByUserId(-1L)).hasSize(2);
        postService.deleteAllPostOfUser(-1L);
        assertThat(postRepository.findAllByUserId(-1L)).hasSize(0);
    }
}
