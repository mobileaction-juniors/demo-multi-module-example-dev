package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.IPostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UserControllerTest {

    private MockMvc mvc;
    private IPostService postService;

    @BeforeEach
    void setUp() 
    {
        postService = mock(IPostService.class);
        UserController controller = new UserController(postService);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void deleteAllPostsOfUser_returnsOkAndCallsService() throws Exception 
    {
        long userId = 42L;

        mvc.perform(delete("/api/users/{userId}/posts", userId))
           .andExpect(status().isOk());

        verify(postService, times(1)).deleteAllPostsOfUser(userId);
        verifyNoMoreInteractions(postService);
    }
}
