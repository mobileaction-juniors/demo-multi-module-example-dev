package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;

import java.util.List;

public interface IUserService
{
    void saveUser(User user);

    List<Long> getAllDistinctUserIds();
}
