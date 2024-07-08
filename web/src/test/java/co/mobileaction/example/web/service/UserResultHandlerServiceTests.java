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
 * @author Mehmet Akif Sahin
 * @date 08.07.2024
 * @time 09:56
 */
@ExtendWith(MockitoExtension.class)
class UserResultHandlerServiceTests
{
    @InjectMocks
    private UserResultHandlerService userResultHandlerService;

    @Mock
    private IUserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Test
    void executeMessage()
    {
        UserDto userDto = UserDto.builder()
                .id(3L)
                .name("name-3")
                .username("username-3")
                .email("email-3")
                .build();

        userResultHandlerService.executeMessage(userDto);

        verify(userService).saveUser(userArgumentCaptor.capture());
        assertThat(userArgumentCaptor.getValue().getId()).isEqualTo(userDto.getId());
    }
}
