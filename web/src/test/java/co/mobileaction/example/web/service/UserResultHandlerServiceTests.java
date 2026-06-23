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

/**
 * @author berkturk
 * @date 22.06.2026
 * @time 17:22
 */
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
        UserDto user = UserDto.builder()
                .id(5L)
                .name("name")
                .username("username")
                .phone("phone")
                .email("email")
                .website("website")
                .build();

        userResultHandlerService.executeMessage(user);

        verify(userService).saveUser(userArgumentCaptor.capture());
        assertThat(userArgumentCaptor.getValue().getId()).isEqualTo(5L);
    }
}
