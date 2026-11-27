package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserResultHandlerServiceTests
{
    @InjectMocks
    private UserResultHandlerService userResultHandlerService;

    @Mock
    private IUserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Test
    public void executeMessage_ValidUserDto_SavesConvertedUser()
    {
        UserDto userDto = UserDto.builder()
                .id(5L)
                .name("name-5")
                .username("username-5")
                .email("user5@test.com")
                .build();

        userResultHandlerService.executeMessage(userDto);

        verify(userService).saveUser(userArgumentCaptor.capture());

        User user = userArgumentCaptor.getValue();
        assertThat(user.getId()).isEqualTo(5L);
        assertThat(user.getName()).isEqualTo("name-5");
        assertThat(user.getUsername()).isEqualTo("username-5");
        assertThat(user.getEmail()).isEqualTo("user5@test.com");
    }
}
