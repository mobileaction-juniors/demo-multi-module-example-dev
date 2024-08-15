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
    public void deleteAllPostsOfUser() {
        // Before deletion, verify the posts for user 1L exist
        List<Post> postsBeforeDeletion = postService.findAllPostsOfUser(1L);
        assertThat(postsBeforeDeletion).hasSize(2); // Assuming user 1L has 2 posts

        // Perform the deletion
        postService.deleteAllPostsOfUser(1L);

        // After deletion, verify no posts exist for user 1L
        List<Post> postsAfterDeletion = postService.findAllPostsOfUser(1L);
        assertThat(postsAfterDeletion).isEmpty();

        // Verify the total number of posts in the repository has decreased accordingly
        List<Post> remainingPosts = postRepository.findAll();
        assertThat(remainingPosts).hasSize(2); // Assuming there were initially 4 posts in total
    }

}
