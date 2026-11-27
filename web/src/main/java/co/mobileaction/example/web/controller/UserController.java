package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.IPostService;
import co.mobileaction.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured(SecurityUtils.ROLE_USER)
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController
{
    private final IPostService postService;

    @DeleteMapping("{userId}/posts")
    public ResponseEntity<Boolean> deleteAllPostsOfUser(@PathVariable Long userId)
    {
        postService.deleteAllPostsOfUser(userId);

        return ResponseEntity.ok(true);
    }

}