package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserResultHandlerService implements IUserResultHandlerService
{
    private final IUserService service;


    @Override
    public void executeMessage(UserDto userDto) {
        service.saveUser(convert(userDto));
    }

    private User convert(UserDto userDto)
    {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .website(userDto.getWebsite())
                .build();
    }
}