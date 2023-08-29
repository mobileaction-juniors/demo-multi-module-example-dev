package co.mobileaction.example.worker.client;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:00
 */
public interface ICrawlerClient
{
    PostDto fetchPost(Long postId);

    UserDto fetchUser(Long userId);
}
