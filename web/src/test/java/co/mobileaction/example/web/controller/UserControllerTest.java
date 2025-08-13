package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.IPostService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false) 
class UserControllerTest extends ControllerTestsBase {

    @MockBean
    private IPostService postService;

    @Test
    void deleteAllPostsOfUser_returnsOkAndCallsService() throws Exception 
    {
        Long userId = 1L;
        doNothing().when(postService).deleteAllPostsOfUser(userId);

        mockMvc.perform(delete("/api/users/{userId}/posts", userId))
               .andExpect(status().isOk());

        verify(postService).deleteAllPostsOfUser(userId);
    }
}
