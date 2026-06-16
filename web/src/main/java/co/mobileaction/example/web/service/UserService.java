package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.exception.UserFoundException;
import co.mobileaction.example.web.exception.UserNotFoundException;
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
    public List<UserDto> findUsers(Pageable pageable)
    {
        List<User> users = userRepository.findAll(pageable).getContent();

        if(users.isEmpty()){ throw new UserNotFoundException(); }

        return users.stream().map(user -> new UserDto(user.getName(), user.getUsername(), user.getEmail())).toList();
    }

    @Override
    public void saveUser(User user)
    {
        if(userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail()))
        {
                throw new UserFoundException();
        }

        userRepository.save(user);
    }



}
