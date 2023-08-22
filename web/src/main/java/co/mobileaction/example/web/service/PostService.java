package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.repository.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:46
 */
@Service
@RequiredArgsConstructor
public class PostService implements IPostService
{
    private final IPostRepository postRepository;

    @Override
    public void savePost(Post post)
    {
        postRepository.save(post);
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

    @Override
    public void deletePostsByUser(Long userId)
    {
        postRepository.deleteAllByUserId(userId);
    }

    /*
    @Override
    public List<Long> distinctUserIds()
    {
        return postRepository.getAllDistinctIds();

    }
    */



}
