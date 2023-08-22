package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.IUserQueueService;
import co.mobileaction.example.web.service.IPostQueueService;
import co.mobileaction.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:55
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

    @PostMapping("queue/users")
    public ResponseEntity<Boolean> createQueueRequestsForUsers()
    {
        userQueueService.sendUserRequestForAllItems();

        return ResponseEntity.ok(true);
    }


}
