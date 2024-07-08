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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Mehmet Akif Sahin
 * @date 08.07.2024
 * @time 08:58
 */
@WebMvcTest(controllers = UserController.class)
@ContextConfiguration(classes = UserController.class)
@WithMockUser(roles = {SecurityUtils.USER})
class UserControllerTests extends ControllerTestsBase
{
    @MockBean
    private IUserService userService;

    @Test
    void getUsers() throws Exception
    {
        PageRequest page = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
        when(userService.findUsers(page)).thenReturn(List.of(new User()));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(userService).findUsers(page);
    }

    @Test
    void deleteAllPosts() throws Exception
    {
        Long mockUserId = 1L;

        mockMvc.perform(delete("/api/users/{mockUserId}/posts", mockUserId))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(userService).deleteAllPosts(mockUserId);
    }
}
