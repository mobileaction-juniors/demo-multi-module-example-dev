package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.IPostQueueService;
import co.mobileaction.example.web.service.IUserQueueService;
import co.mobileaction.example.web.service.IUserService;
import co.mobileaction.example.web.util.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author sa
 * @date 17.05.2021
 * @time 18:32
 */
@WebMvcTest(controllers = AdminController.class)
@ContextConfiguration(classes = AdminController.class)
@WithMockUser(roles = {SecurityUtils.ADMIN})
public class AdminControllerTests extends ControllerTestsBase
{
    @MockBean
    private IPostQueueService queueService;

    @MockBean
    private IUserQueueService userQueueService;

    @Test
    public void createQueueRequests() throws Exception
    {
        this.mockMvc.perform(post("/api/admin/queue/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(queueService).sendPostRequestForAllItems();
    }


    @Test
    public void createQueueRequestsForUsers() throws Exception
    {
        this.mockMvc.perform(post("/api/admin/queue/users"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(userQueueService).sendUserRequestForAllItems();
    }
}
