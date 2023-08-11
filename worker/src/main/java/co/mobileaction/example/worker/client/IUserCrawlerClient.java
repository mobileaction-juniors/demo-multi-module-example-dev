package co.mobileaction.example.worker.client;

import co.mobileaction.example.common.dto.UserDto;

public interface IUserCrawlerClient
{
    UserDto fetchUser(Long userId);
}
