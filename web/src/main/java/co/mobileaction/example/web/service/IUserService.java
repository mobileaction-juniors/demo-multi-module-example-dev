package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Mehmet Akif Sahin
 * @date 02.07.2024
 * @time 14:45
 */
public interface IUserService
{
    void deleteAllPosts(Long userId);

    List<Long> findDistinctUserIds();

    void saveUser(User user);

    List<User> findUsers(Pageable pageable);
}
