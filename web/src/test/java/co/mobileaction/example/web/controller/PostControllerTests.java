package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.service.IPostService;
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

/**
 * @author sa
 * @date 17.05.2021
 * @time 18:58
 */
@WebMvcTest(controllers = PostController.class)
@ContextConfiguration(classes = PostController.class)
@WithMockUser(roles = {SecurityUtils.USER})
public class PostControllerTests extends ControllerTestsBase
{
    @MockBean
    private IPostService postService;

    @Test
    public void getPosts() throws Exception
    {
        var page = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        when(postService.findPosts(page)).thenReturn(List.of(new Post()));

        this.mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(postService).findPosts(page);
    }

    @Test
    public void deletePost() throws Exception
    {
        this.mockMvc.perform(delete("/api/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(postService).deletePost(1L);
    }

    @Test
    public void deletePostsByUser() throws Exception
    {
        this.mockMvc.perform(delete("/api/posts/user/1"))
                .andExpect(status().isOk());

        verify(postService).deletePostsByUser(1L);
    }

}
