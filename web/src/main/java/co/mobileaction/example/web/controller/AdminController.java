package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.IPostQueueService;
import co.mobileaction.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:55
 */
@RestController
@Secured(SecurityUtils.ROLE_ADMIN)
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final IPostQueueService postQueueService;

    @PostMapping("queue/posts")
    public ResponseEntity<Boolean> createQueueRequests() 
    {
        postQueueService.sendPostRequestForAllItems();
        return ResponseEntity.ok(true);
    }

    @PostMapping("/crawl-users-from-posts")
    public ResponseEntity<Map<String, Object>> crawlUsersFromPosts() 
    {
        int enqueued = postQueueService.enqueueDistinctUserIdsForCrawl();
        return ResponseEntity.ok(Map.of("enqueued", enqueued));
    }
}
