package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.service.IPostService;
import co.mobileaction.example.web.util.SecurityUtils;
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
 * @date 02.07.2024
 * @time 16.44
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

    //Now we can post Post
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        postService.savePost(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    //Update Post
    @PutMapping("{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId,@RequestBody Post post){
        postService.updatePost(postId,post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long postId)
    {
        postService.deletePost(postId);

        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Boolean> deleteUserPosts(@PathVariable Long userId)
    {
        postService.deleteUserPosts(userId);

        return ResponseEntity.ok(true);
    }


}
