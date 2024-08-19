package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IPostRepository;
import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTests {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IPostRepository postRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("Test User");

        // Act
        userService.saveUser(user);

        // Assert
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetDistinctIds() {
        // Arrange
        List<Long> distinctIds = Arrays.asList(1L, 2L, 3L);
        when(postRepository.findDistinctUserIdBy()).thenReturn(distinctIds);

        // Act
        List<Long> result = userService.getDistinctIds();

        // Assert
        assertEquals(distinctIds, result);
        verify(postRepository, times(1)).findDistinctUserIdBy();
    }
}
