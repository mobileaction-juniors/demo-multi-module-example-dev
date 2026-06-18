package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IPostRepository;
import co.mobileaction.example.web.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService
{
    private final IPostRepository postRepository;
    private final IUserRepository userRepository;

    @Override
    public void deleteAllPostsOfUser(Long userId)
    {
        postRepository.deleteAll(postRepository.findAllByUserId(userId));
    }

    @Override
    public void saveUser(User user)
    {
        userRepository.save(user);
    }
}
