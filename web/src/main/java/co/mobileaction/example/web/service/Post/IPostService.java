package co.mobileaction.example.web.service.Post;

import co.mobileaction.example.web.model.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:46
 */
public interface IPostService
{
    void savePost(Post post);

    List<Post> findPosts(Pageable pageable);

    List<Post> findAllPostsOfUser(Long userId);

    void deletePost(Long postId);

    List<Long> findDistinctUsers();

    void deleteAllPost(Long userId);
}
