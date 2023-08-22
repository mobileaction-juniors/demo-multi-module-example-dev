package co.mobileaction.example.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.mobileaction.example.web.model.User;
import co.mobileaction.example.common.dto.UserDto;

@Service
@RequiredArgsConstructor
public class UserResultHandlerService implements IUserResultHandlerService{
    private final IUserService userService ;

    @Override
    public void executeMessage(UserDto userDto) {
        userService.saveUser(convertFrom(userDto));
    }

    private User convertFrom(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .build();
    }

}
