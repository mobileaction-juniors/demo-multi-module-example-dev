package co.mobileaction.example.web.service.User;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService
{
    private IUserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
