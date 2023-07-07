package co.mobileaction.example.worker.client;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.UserDto;

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
