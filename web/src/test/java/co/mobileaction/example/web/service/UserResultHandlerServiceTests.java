package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.model.dto.CreateUserCmd;
import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserResultHandlerServiceTests {
    @InjectMocks
    UserResultHandlerService userResultHandlerService;

    @Mock
    private IUserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Test
    public void saveUser()
    {
        UserDto dto = new UserDto(5L, "Sezer", "sezer", "sezer@sezer.com");

        userResultHandlerService.executeMessage(dto);

        verify(userRepository).save(userArgumentCaptor.capture());
        assertThat(userArgumentCaptor.getValue().getId()).isEqualTo(5L);
    }
}
