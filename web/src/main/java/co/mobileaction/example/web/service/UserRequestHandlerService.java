package co.mobileaction.example.web.service;


import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRequestHandlerService implements IUserRequestHandlerService
{
    private final IUserService userService;

    @Override
    public void executeMessage(UserDto userDto)
    {
        userService.saveUser(convertFrom(userDto));
    }

    public User convertFrom(UserDto userDto)
    {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .build();
    }
}
