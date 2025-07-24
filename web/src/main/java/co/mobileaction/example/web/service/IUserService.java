package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import java.util.List;

public interface IUserService {
    List<User> findAll();
    void saveUser(User user);
    User findById(Long userId);
}
