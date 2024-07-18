package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.web.model.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:46
 */
public interface IPostService
{
    void savePost(Post post);

    Post convertFrom(PostDto postDto);

    List<Post> findPosts(Pageable pageable);

    List<Post> findAllPostsOfUser(Long userId);

    void deletePost(Long postId);

    Set<Long> findDistinctUsersFromPosts();

    void deleteAllPostsOfUser(Long userId);
}
