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
        userService.saveUser(User.from(userDto));
    }
}