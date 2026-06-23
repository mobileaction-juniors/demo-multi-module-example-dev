package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author berkturk
 * @date 22.06.2026
 * @time 17:06
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService
{
    private final IUserRepository userRepository;

    @Override
    public void saveUser(User user)
    {
        userRepository.save(user);
    }
}
