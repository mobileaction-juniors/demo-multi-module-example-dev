package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserResultHandlerService implements IUserResultHandlerService
{
    private final IUserService userService;

    @Override
    public void executeMessage(UserDto userDto)
    {
        userService.saveUser(convertFrom(userDto));
    }

    private User convertFrom(UserDto userDto)
    {
        return User.builder()
                .name(userDto.name())
                .username(userDto.username())
                .email(userDto.email())
                .build();
    }
}
