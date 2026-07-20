package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.LocalUser;
import org.springframework.stereotype.Service;

@Service
public class UserResultHandlerService implements IUserResultHandlerService
{
    private final IUserService userService;

    public UserResultHandlerService(IUserService userService)
    {
        this.userService = userService;
    }

    @Override
    public void executeMessage(UserDto userDto)
    {
        userService.saveUser(convertFrom(userDto));
    }

    private LocalUser convertFrom(UserDto userDto)
    {
        return LocalUser.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .build();
    }
}
