package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserMockTests
{
    @Mock
    private IUserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp()
    {
        userService = new UserService(userRepository);
    }

    @Test
    public void findUsers()
    {
        List<User> list = Arrays.asList(
                new User(1L, "user1", "user1", "user1"),
                new User(2L, "user1", "user1", "user1"),
                new User(3L, "user1", "user1", "user1")
        );

        when(userRepository.findAll()).thenReturn(list);

        userService.findUsers();

        verify(userRepository).findAll();
    }
}
