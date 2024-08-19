package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.IUserQueueService;
import co.mobileaction.example.web.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTests {

    @Mock
    private IUserService userService;

    @Mock
    private IUserQueueService userQueueService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUsers() {
        // Arrange
        List<Long> mockIds = Arrays.asList(1L, 2L, 3L);
        when(userService.getDistinctIds()).thenReturn(mockIds);

        // Act
        ResponseEntity<List<Long>> response = userController.getUsers();

        // Assert
        verify(userQueueService, times(1)).sendRequestForItems();
        verify(userService, times(1)).getDistinctIds();
        assertEquals(ResponseEntity.ok(mockIds), response);
    }
}
