package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.LocalUser;
import co.mobileaction.example.web.repository.IUserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService
{
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(LocalUser user)
    {
        userRepository.save(user);
    }

    @Override
    public List<LocalUser> findUsers(Pageable pageable)
    {
        return userRepository.findAll(pageable).getContent();
    }
}
