package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    void saveUser(User user);


    List<Long> getAllDistinctIds();


}
