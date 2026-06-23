package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author berkturk
 * @date 22.06.2026
 * @time 17:10
 */
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
                .id(userDto.getId())
                .name(userDto.getName())
                .username(userDto.getUsername())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .website(userDto.getWebsite())
                .build();
    }
}
