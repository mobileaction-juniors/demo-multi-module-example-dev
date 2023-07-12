package co.mobileaction.example.web.controller;


import co.mobileaction.example.web.service.IPostService;
import co.mobileaction.example.web.service.IUserService;
import co.mobileaction.example.web.util.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@ContextConfiguration(classes = UserController.class)
@WithMockUser(roles = {SecurityUtils.USER})
public class UserControllerTests extends ControllerTestsBase
{
     @MockBean
     private IPostService postService;

     @Test
        public void queueAllDistinctUserIds() throws Exception
        {
            this.mockMvc.perform(post("/api/users/userIds"))
                    .andExpect(status().isOk());

            verify(postService).queueUniqueUserIds();
        }
}
