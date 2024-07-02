package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author sa
 * @date 02.07.2024
 * @time 16.43
 */
public interface IPostService
{
    void deleteUserPosts(Long userId);

    void savePost(Post post);

    void updatePost(Long postId, Post post);

    List<Post> findPosts(Pageable pageable);

    List<Post> findAllPostsOfUser(Long userId);

    void deletePost(Long postId);
}
