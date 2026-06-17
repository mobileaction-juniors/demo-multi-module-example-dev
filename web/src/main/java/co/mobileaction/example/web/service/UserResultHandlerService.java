package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserResultHandlerService implements IUserResultHandlerService
{
    private final IUserService userService;
    private final AmqpTemplate userResultProblemQueueTemplate;

    @Override
    public void executeMessage(UserDto userDto)
    {
        try
        {
            userService.saveUser(convertFrom(userDto));
        }
        catch (Exception e)
        {
            log.error("Could not handle user result for username: {}", userDto.username(), e);

            userResultProblemQueueTemplate.convertAndSend(userDto);
        }
    }

    private User convertFrom(UserDto userDto)
    {
        return User.builder()
                .name(userDto.name())
                .username(userDto.username())
                .email(userDto.email())
                .build();
    }
}
