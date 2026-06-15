package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
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
        return userRepository.findAll(pageable).getContent().stream().map(user -> new UserDto(user.getName(), user.getUsername(), user.getEmail())).toList();
    }

    @Override
    public void saveUser(User user) { userRepository.save(user); }

}
