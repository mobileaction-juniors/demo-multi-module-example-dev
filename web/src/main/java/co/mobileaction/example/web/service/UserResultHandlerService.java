package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserResultHandlerService implements IUserResultHandlerService
{
    private final IUserService userService;

    @Override
    public void executeMessage(UserDto userDto)
    {
        if (userDto == null) {
            log.warn("Received null UserDto, skipping save operation");
            return;
        }

        if (userDto.getId() == null) {
            log.warn("UserDto has null ID, skipping save: name={}, username={}", 
                    userDto.getName(), userDto.getUsername());
            return;
        }

        if (userDto.getName() == null || userDto.getUsername() == null) {
            log.warn("UserDto has null name or username, skipping save: id={}, name={}, username={}", 
                    userDto.getId(), userDto.getName(), userDto.getUsername());
            return;
        }

        try {
            User user = User.fromDto(userDto);
            userService.saveUser(user);
            log.debug("Successfully saved user with ID: {}", user.getId());
        } catch (Exception e) {
            log.error("Failed to save user from DTO: {}", userDto, e);
            throw e; // Re-throw to trigger queue handler error handling
        }
    }
}