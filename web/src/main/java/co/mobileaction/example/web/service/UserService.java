package co.mobileaction.example.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author berkturk
 * @date 23.06.2026
 * @time 10:52
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService
{
    private final IPostService postService;

    @Override
    @Transactional
    public void deleteAllPostsOfUser(Long userId)
    {
        postService.deleteAllPostsOfUser(userId);
    }
}
