package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.IUserService;
import co.mobileaction.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author berkturk
 * @date 23.06.2026
 * @time 10:58
 */
@RestController
@Secured(SecurityUtils.ROLE_USER)
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController
{
    private final IUserService userService;

    @DeleteMapping("{userId}/posts")
    public ResponseEntity<Boolean> deleteAllPostsOfUser(@PathVariable Long userId)
    {
        userService.deleteAllPostsOfUser(userId);

        return ResponseEntity.ok(true);
    }
}
