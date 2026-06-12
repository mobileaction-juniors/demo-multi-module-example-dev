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
 * @author serkankorkut
 * @date 12.06.2026
 * @time 14:55
 */
@RestController
@Secured(SecurityUtils.ROLE_USER)
@RequestMapping("api/users/{userId}/posts")
@RequiredArgsConstructor
public class UserController
{
    private final IUserService userService;

    @DeleteMapping
    public ResponseEntity<Boolean> deleteAllPostsOfUser(@PathVariable Long userId)
    {
        userService.deleteAllPostsOfUser(userId);

        return ResponseEntity.ok(true);
    }

}
