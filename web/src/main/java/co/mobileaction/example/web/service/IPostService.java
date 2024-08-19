package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface IPostService
{
    void savePost(Post post);

    List<Post> findPosts(Pageable pageable);

    List<Post> findAllPostsOfUser(Long userId);

    void deletePost(Long postId);

    void deletePostByUserId(Long userId);

    Set<Long> findDistinctUserIds();
}
