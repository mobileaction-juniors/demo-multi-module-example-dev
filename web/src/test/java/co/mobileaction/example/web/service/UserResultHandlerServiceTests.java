package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.any;

/**
 * @author sa
 * @date 17.05.2021
 * @time 18:15
 */
@ExtendWith(MockitoExtension.class)
class UserResultHandlerServiceTests
{
    @Mock
    private IUserService userService;

    @InjectMocks
    private UserResultHandlerService userResultHandlerService;

    @Test
    void executeMessage_ShouldConvertAndSaveUser()
    {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .name("John Doe")
                .username("johndoe")
                .build();
        
        userResultHandlerService.executeMessage(userDto);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userService).saveUser(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertThat(savedUser.getId()).isEqualTo(1L);
        assertThat(savedUser.getName()).isEqualTo("John Doe");
        assertThat(savedUser.getUsername()).isEqualTo("johndoe");
    }

    @Test
    void executeMessage_ShouldSkipSaveWhenUserDtoIsNull()
    {
        userResultHandlerService.executeMessage(null);

        verify(userService, never()).saveUser(any());
    }

    @Test
    void executeMessage_ShouldSkipSaveWhenUserIdIsNull()
    {
        UserDto userDto = UserDto.builder()
                .id(null)
                .name("John Doe")
                .username("johndoe")
                .build();

        userResultHandlerService.executeMessage(userDto);

        verify(userService, never()).saveUser(any());
    }

    @Test
    void executeMessage_ShouldSkipSaveWhenNameIsNull()
    {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .name(null)
                .username("johndoe")
                .build();

        userResultHandlerService.executeMessage(userDto);

        verify(userService, never()).saveUser(any());
    }
}