package co.mobileaction.example.web.service;

import co.mobileaction.example.web.repository.IPostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author serkankorkut
 * @date 12.06.2026
 * @time 15:02
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService
{
    private final IPostRepository postRepository;

    @Override
    @Transactional
    public void deleteAllPostsOfUser(Long userId)
    {
        postRepository.deleteAllByUserId(userId);
    }
}
