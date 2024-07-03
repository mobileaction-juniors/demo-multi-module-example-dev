package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.IPostService;
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
 * @author Mehmet Akif Sahin
 * @date 2.07.2024
 * @time 14:42
 */
@RestController
@Secured(SecurityUtils.ROLE_USER)
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController
{
    private final IUserService userService;

    @DeleteMapping("{userId}")
    public ResponseEntity<Boolean> deleteAllPosts(@PathVariable Long userId)
    {
        userService.deleteAllPosts(userId);

        return ResponseEntity.ok(true);
    }
}
