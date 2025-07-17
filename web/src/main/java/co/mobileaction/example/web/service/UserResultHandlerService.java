package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserCrawlRequestDto;
import co.mobileaction.example.web.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserResultHandlerService implements IUserResultHandlerService {
    private final IUserService userService;

    @Override
    public void executeMessage(UserCrawlRequestDto userDto)
    {
        userService.saveUser(convertFrom(userDto));
    }

    @Override
    public User convertFrom(UserCrawlRequestDto userDto)
    {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .username(userDto.getUsername())
                .build();
    }
}