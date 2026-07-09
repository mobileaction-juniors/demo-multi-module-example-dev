package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.IUserQueueService;
import co.mobileaction.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yunus Gunay
 */
@RestController
@Secured(SecurityUtils.ROLE_ADMIN)
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController
{
    private final IUserQueueService userQueueService;

    @PostMapping("crawl")
    public ResponseEntity<Boolean> crawlUsers()
    {
        userQueueService.sendUserRequestForAllPostUsers();

        return ResponseEntity.ok(true);
    }
}
