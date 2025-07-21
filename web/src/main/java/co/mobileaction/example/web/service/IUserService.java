package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import java.util.List;

public interface IUserService {
    void saveUser(User user);

    void saveAllUsers(List<User> users);

    User findUserById(Long id);

    List<User> findAllUsers();
}