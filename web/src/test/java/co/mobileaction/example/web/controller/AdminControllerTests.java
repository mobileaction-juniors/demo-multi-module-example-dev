package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.IPostQueueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AdminControllerTests
{

    private MockMvc mvc;
    private IPostQueueService postQueueService;

    @BeforeEach
    void setUp() 
    {
        postQueueService = mock(IPostQueueService.class);
        AdminController controller = new AdminController(postQueueService);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void createQueueRequests_callsSendPostRequestForAllItems_andReturnsTrue() throws Exception 
    {
        mvc.perform(post("/api/admin/queue/posts"))
           .andExpect(status().isOk())
           .andExpect(content().string("true"));

        verify(postQueueService, times(1)).sendPostRequestForAllItems();
        verifyNoMoreInteractions(postQueueService);
    }

    @Test
    void crawlUsersFromPosts_callsEnqueueDistinct_andReturnsCount() throws Exception 
    {
        when(postQueueService.enqueueDistinctUserIdsForCrawl()).thenReturn(3);

        mvc.perform(post("/api/admin/crawl-users-from-posts"))
           .andExpect(status().isOk())
           .andExpect(content().json("{\"enqueued\":3}"));

        verify(postQueueService, times(1)).enqueueDistinctUserIdsForCrawl();
        verifyNoMoreInteractions(postQueueService);
    }
}
