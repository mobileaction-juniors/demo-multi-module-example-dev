package co.mobileaction.example.web.service;

import co.mobileaction.example.web.repository.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService
{
    private final IPostRepository postRepository;

    @Override
    public void deleteAllPostsOfUser(Long userId)
    {
        postRepository.deleteAll(postRepository.findAllByUserId(userId));
    }
}
