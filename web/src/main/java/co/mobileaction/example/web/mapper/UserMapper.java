package co.mobileaction.example.web.mapper;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;

public class UserMapper
{

    public static User fromDto(UserDto userDto)
    {
        return User.builder()
                .name(userDto.getName())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .build();
    }
}
