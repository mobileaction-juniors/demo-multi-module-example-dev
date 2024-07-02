package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.repository.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author sa
 * @date 02.07.2024
 * @time 16.44
 */
@Service
@RequiredArgsConstructor
public class PostService implements IPostService
{
    private final IPostRepository postRepository;

    @Transactional
    @Override
    public void deleteUserPosts(Long userId) {
       postRepository.deletePosts(userId);
    }

    @Override
    public void savePost(Post post)
    {
        postRepository.save(post);
    }

    @Override
    public void updatePost(Long postId, Post post)
    {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();

            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());
            //existingPost.setUserId(post.getUserId());
            //existingPost.setId(post.getId());

            postRepository.save(existingPost);
        }
    }

    @Override
    public List<Post> findPosts(Pageable pageable)
    {
        return postRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Post> findAllPostsOfUser(Long userId)
    {
        return postRepository.findAllByUserId(userId);
    }

    @Override
    public void deletePost(Long postId)
    {
        postRepository.deleteById(postId);
    }


}
