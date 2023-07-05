package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.IPostQueueService;
import co.mobileaction.example.web.service.IUserQueueService;
import co.mobileaction.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elif
 * @date 05.07.2023
 * @time 16.00
 */
@RestController
@Secured(SecurityUtils.ROLE_ADMIN)
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController
{
    private final IPostQueueService queueService;
    private final IUserQueueService userQueueService;

    @PostMapping("queue/posts")
    public ResponseEntity<Boolean> createQueueRequests()
    {
        queueService.sendPostRequestForAllItems();

        return ResponseEntity.ok(true);
    }

    @PostMapping("queue/user")
    public ResponseEntity<Boolean> registerUserWithQueue()
    {
        userQueueService.sendUserRequestForAllPosts();

        return ResponseEntity.ok(true);
    }
}
