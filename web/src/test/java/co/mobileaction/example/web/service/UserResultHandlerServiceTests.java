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
    public void saveUser()
    {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .name("name-1")
                .username("username-1")
                .email("email-1@mail.com")
                .build();

        userResultHandlerService.executeMessage(userDto);

        verify(userService).saveUser(userArgumentCaptor.capture());
        assertThat(userArgumentCaptor.getValue().getId()).isEqualTo(1L);
        assertThat(userArgumentCaptor.getValue().getName()).isEqualTo("name-1");
    }
}
