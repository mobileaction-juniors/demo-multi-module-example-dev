package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IPostRepository;
import co.mobileaction.example.web.repository.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mehmet Akif Sahin
 * @date 07.02.2024
 * @time 15:00
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService
{
    private final IPostRepository postRepository;
    private final IUserRepository userRepository;

    @Override
    @Transactional
    public void deleteAllPosts(Long userId)
    {
        postRepository.deleteAllByUserId(userId);
    }

    @Override
    public List<Long> getDistinctUserIds()
    {
        return postRepository.getDistinctUserIds();
    }

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
}
