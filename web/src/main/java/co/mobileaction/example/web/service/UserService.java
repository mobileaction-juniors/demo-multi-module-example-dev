package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IPostRepository;
import co.mobileaction.example.web.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elif ece can
 * @date 04.07.2024
 * @time 17:57
 */

@Service
@RequiredArgsConstructor
public class UserService implements IUserService
{
    private final IUserRepository userRepository;
    private final IPostRepository postRepository;

    @Override
    public void saveUser(User user)
    {
        userRepository.save(user);
    }

    @Override
    public List<User> findUsers(Pageable pageable)
    {
        return userRepository.findAll(pageable).getContent();
    }

    @Override
    public void deleteUser(Long userId)
    {
        userRepository.deleteById(userId);
    }

    @Override
    public List<Long> getDistinctIds() { return postRepository.getDistinctIds(); }
}
