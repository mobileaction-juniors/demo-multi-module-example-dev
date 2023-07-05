package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.exception.AlreadyExistException;
import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:39
 */
@Service
@RequiredArgsConstructor
public class UserResultHandlerService implements IUserResultHandlerService
{
    private final IUserService userService;

    @Override
    public void executeMessage(UserDto userDto) throws AlreadyExistException
    {
        userService.saveUser(convertFrom(userDto));
    }

    private User convertFrom(UserDto userDto)
    {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .build();
    }
}
