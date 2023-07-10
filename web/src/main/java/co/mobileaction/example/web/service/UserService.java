package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

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

    @Override
    public List<User> findUsers(Pageable pageable) { return userRepository.findAll(pageable).getContent(); }

    public List<Long> findAllDistinctUserIds() { return userRepository.findAllDistinctUserIds(); }


}
