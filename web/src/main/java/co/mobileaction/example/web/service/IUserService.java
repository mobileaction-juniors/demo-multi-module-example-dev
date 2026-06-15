package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;

public interface IUserService
{
    void deleteAllPostsOfUser(Long userId);

    void saveUser(User user);
}
