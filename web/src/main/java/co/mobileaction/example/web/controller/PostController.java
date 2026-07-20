package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.service.IPostService;
import co.mobileaction.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sa
 * @date 17.05.2021
 * @time 18:07
 */
@RestController
@Secured(SecurityUtils.ROLE_USER)
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController
{
    private final IPostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(@PageableDefault(size = 10)
                                               @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable)
    {
        return ResponseEntity.ok(postService.findPosts(pageable));
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long postId)
    {
        postService.deletePost(postId);

        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteAllPostsOfUser(@PathVariable Long userId)
    {
        try
        {
            boolean isDeleted = postService.deleteAllPostsOfUser(userId);
            if (!isDeleted) {
                throw new Exception("something went wrong");
            }
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        return ResponseEntity.ok(String.format("all posts of user %d deleted", userId));
    }

    @GetMapping("/users")
    public ResponseEntity<List<Long>> getDistinctUsers()
    {
        List<Long> distinctUsers = postService.findDistinctUsers();
        return ResponseEntity.ok(distinctUsers);
    }
}
