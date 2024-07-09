package co.mobileaction.example.web.service;


import co.mobileaction.example.web.model.Post;
import org.springframework.data.domain.Pageable;

import co.mobileaction.example.web.model.User;

import java.util.List;

public interface IUserService {
    void saveUser(User user);

    List<User> findUsers(Pageable pageable);

}
