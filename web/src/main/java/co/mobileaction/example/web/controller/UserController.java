package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.IPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IPostService postService;

    public UserController(IPostService postService) {
        this.postService = postService;
    }

    // DELETE /api/users/{userId}/posts
    @DeleteMapping("/{userId}/posts")
    public ResponseEntity<Void> deleteAllPostsOfUser(@PathVariable Long userId)
    {
        postService.deleteAllPostsOfUser(userId);
        return ResponseEntity.ok().build();
    }
}
