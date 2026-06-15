package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.IUserService;
import co.mobileaction.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * @author sa
 * @date 17.05.2021
 */
@RestController
@Secured(SecurityUtils.ROLE_USER)
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController
{
    private final IUserService userService;

    @DeleteMapping("{userId}/posts")
    public ResponseEntity<Boolean> deleteAllPosts(@PathVariable Long userId)
    {
        userService.deleteAllPostsOfUser(userId);

        return ResponseEntity.ok(true);
    }
}
