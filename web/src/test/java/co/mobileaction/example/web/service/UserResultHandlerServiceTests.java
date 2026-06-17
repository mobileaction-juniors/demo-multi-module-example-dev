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
import org.springframework.amqp.core.AmqpTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserResultHandlerServiceTests
{
    @InjectMocks
    private UserResultHandlerService userResultHandlerService;

    @Mock
    private IUserService userService;

    @Mock(name = "userResultProblemQueueTemplate")
    private AmqpTemplate userResultProblemQueueTemplate;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    public void test_executeMessage()
    {
        UserDto userDto = new UserDto("name-1", "username-1", "email-1");

        userResultHandlerService.executeMessage(userDto);

        verify(userService).saveUser(userCaptor.capture());
        assertThat(userCaptor.getValue().getName()).isEqualTo("name-1");
        assertThat(userCaptor.getValue().getUsername()).isEqualTo("username-1");
        assertThat(userCaptor.getValue().getEmail()).isEqualTo("email-1");
    }

    @Test
    public void test_executeMessage_fail()
    {
        UserDto userDto = new UserDto("name-1", "username-1", "email-1");

        doThrow(RuntimeException.class).when(userService).saveUser(any());

        userResultHandlerService.executeMessage(userDto);

        verify(userResultProblemQueueTemplate).convertAndSend(userDto);
    }
}
