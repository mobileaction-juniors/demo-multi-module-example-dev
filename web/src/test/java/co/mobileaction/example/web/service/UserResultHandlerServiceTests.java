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
public class UserResultHandlerServiceTests {

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
                .id(11L)
                .name("Tarik Firat")
                .email("trkfrt@gmail.com")
                .phone("05555555555")
                .username("trkfrt0044")
                .website("trkfrt.com")
                .build();

        userResultHandlerService.executeMessage(user);

        verify(userService).saveUser(userArgumentCaptor.capture());
        assertThat(userArgumentCaptor.getValue().getId()).isEqualTo(11L);
    }


}
