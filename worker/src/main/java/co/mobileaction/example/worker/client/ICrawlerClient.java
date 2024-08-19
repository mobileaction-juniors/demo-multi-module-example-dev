package co.mobileaction.example.worker.client;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.UserDto;

public interface ICrawlerClient
{
    PostDto fetchPost(Long postId);

    UserDto fetchUser(Long userId);
}
