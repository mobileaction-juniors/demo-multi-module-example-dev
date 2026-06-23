package co.mobileaction.example.web.service;

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
    private final PostService postService;

    @Override
    public void deleteAllPostsOfUser(Long userId)
    {
        postService.deleteAllPostsOfUser(userId);
    }
}
