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
                .name("Leanne Graham")
                .username("Bret")
                .email("Sincere@april.biz")
                .build();

        userResultHandlerService.executeMessage(userDto);

        verify(userService).saveUser(userArgumentCaptor.capture());
        
        User savedUser = userArgumentCaptor.getValue();
        assertThat(savedUser.getId()).isEqualTo(1L);
        assertThat(savedUser.getName()).isEqualTo("Leanne Graham");
        assertThat(savedUser.getUsername()).isEqualTo("Bret");
        assertThat(savedUser.getEmail()).isEqualTo("Sincere@april.biz");
    }
}
