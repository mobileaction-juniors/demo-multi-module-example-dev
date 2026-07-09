package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Yunus Gunay
 */
@Service
@RequiredArgsConstructor
public class UserResultHandlerService implements IUserResultHandlerService
{
    private final IUserRepository userRepository;

    @Override
    public void executeMessage(UserDto userDto)
    {
        userRepository.save(convertFrom(userDto));
    }

    private User convertFrom(UserDto userDto)
    {
        return User.builder()
                .id(userDto.id())
                .name(userDto.name())
                .username(userDto.username())
                .email(userDto.email())
                .build();
    }
}
