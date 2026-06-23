package co.mobileaction.example.web.service;

import co.mobileaction.example.web.repository.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author berkturk
 * @date 23.06.2026
 * @time 10:52
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService
{
    private final IPostRepository postRepository;

    @Override
    public void deleteAllPostsOfUser(Long userId)
    {
        postRepository.deleteAllByUserId(userId);
    }
}
