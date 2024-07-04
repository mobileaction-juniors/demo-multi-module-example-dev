package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.service.IPostQueueService;
import co.mobileaction.example.web.service.IUserQueueService;
import co.mobileaction.example.web.service.IUserService;
import co.mobileaction.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private final IUserService userService;
    private final IPostQueueService queueService;
    private final IUserQueueService userQueueService;

    @PostMapping("queue/posts")
    public ResponseEntity<Boolean> createQueueRequests()
    {
        queueService.sendPostRequestForAllItems();
        return ResponseEntity.ok(true);
    }

    @GetMapping("queue/users")
    public ResponseEntity<List<User>> getUsers(@PageableDefault(size = 10) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable)
    {
        return ResponseEntity.ok(userService.findUsers(pageable));
    }

    @PostMapping("queue/users")
    public ResponseEntity<Boolean> createUserQueueRequests()
    {
        List<Long> userIds = userService.getDistinctUserIds();
        userQueueService.sendUserRequests(userIds);
        return ResponseEntity.ok(true);
    }
}
