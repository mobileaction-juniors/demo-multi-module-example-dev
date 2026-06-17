package co.mobileaction.example.web.controller;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.exception.GlobalExceptionHandler;
import co.mobileaction.example.web.exception.UserFoundException;
import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@ContextConfiguration(classes = {UserController.class, GlobalExceptionHandler.class})
public class UserControllerTests extends ControllerTestsBase
{
    @MockBean
    private IUserService userService;

    @Test
    public void getUsers() throws Exception
    {
        var page = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        when(userService.findUsers(page)).thenReturn(List.of(new UserDto("name-1", "username-1", "email-1")));

        this.mockMvc.perform(get("/api/users")).andExpect(status().isOk());

        verify(userService).findUsers(page);
    }

    @Test
    public void createUser() throws Exception
    {
        this.mockMvc.perform(post("/api/users").contentType("application/json").content("{\"name\":\"name-1\",\"username\":\"username-1\",\"email\":\"email-1\"}")).andExpect(status().isOk());

        verify(userService).saveUser(any(User.class));
    }

    @Test
    public void createUser_duplicateUser_returnsConflict() throws Exception
    {
        doThrow(new UserFoundException()).when(userService).saveUser(any(User.class));

        this.mockMvc.perform(post("/api/users").contentType("application/json").content("{\"name\":\"name-1\",\"username\":\"username-1\",\"email\":\"email-1\"}")).andExpect(status().isConflict());

        verify(userService).saveUser(any(User.class));
    }
}