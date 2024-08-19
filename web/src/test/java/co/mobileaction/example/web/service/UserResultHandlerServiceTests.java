package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserResultHandlerServiceTests {

    @Mock
    private IUserService userService;

    @InjectMocks
    private UserResultHandlerService userResultHandlerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteMessage() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("Test Name");
        userDto.setUsername("test_username");
        userDto.setEmail("test@example.com");
        userDto.setPhone("1234567890");
        userDto.setWebsite("http://example.com");

        User expectedUser = User.builder()
                .id(1L)
                .name("Test Name")
                .username("test_username")
                .email("test@example.com")
                .phone("1234567890")
                .website("http://example.com")
                .build();

        // Act
        userResultHandlerService.executeMessage(userDto);

        // Assert
        verify(userService, times(1)).saveUser(expectedUser);
    }
}
