package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;

import java.awt.print.Pageable;
import java.util.List;

public interface IUserService {

    void saveUser(User user);

    List<User> findUsers();

    void deleteUser(Long id);
}
