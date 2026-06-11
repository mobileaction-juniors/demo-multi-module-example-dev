package co.mobileaction.example.web.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserResultHandlerServiceTest {

    @Mock
    private IUserRepository userRepository;

    private UserResultHandlerService service;

    @BeforeEach
    void setUp() {
        service = new UserResultHandlerService(userRepository);
    }

    //test for saving user asynchronously
    @Test
    void savesUserAsynchronously() {
        UserDto dto = new UserDto(10L, "John Doe", "jdoe", "jdoe@example.com");

        service.executeMessage(dto);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        User saved = captor.getValue();
        assertEquals(dto.getId(), saved.getId());
        assertEquals(dto.getName(), saved.getName());
        assertEquals(dto.getUsername(), saved.getUsername());
        assertEquals(dto.getEmail(), saved.getEmail());
    }
}
