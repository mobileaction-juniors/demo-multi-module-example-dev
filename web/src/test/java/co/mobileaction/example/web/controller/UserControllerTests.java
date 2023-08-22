package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.service.IUserService;
import co.mobileaction.example.web.util.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
@ContextConfiguration(classes = UserController.class)
@WithMockUser(roles = {SecurityUtils.USER})
public class UserControllerTests extends ControllerTestsBase{

    @MockBean
    private IUserService userService;

    @Test
    public void getUsers() throws Exception
    {
        var page = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        when(userService.getAllDistinctIds()).thenReturn(List.of(1L));

        this.mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(userService).getAllDistinctIds();
    }




}
