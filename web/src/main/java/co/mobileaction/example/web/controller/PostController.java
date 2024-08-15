package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.service.IPostService;
import co.mobileaction.example.web.util.SecurityUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
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

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<HttpStatus> deletePostsByUser(@PathVariable Long userId)
    {
        try {
            postService.deleteAllPostsOfUser(userId);
            return ResponseEntity.ok(HttpStatus.OK); // 204 No Content
        } catch (EntityNotFoundException e) {
            // Return 404 Not Found if no posts are found for the given user ID
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Return 500 Internal Server Error for any other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
