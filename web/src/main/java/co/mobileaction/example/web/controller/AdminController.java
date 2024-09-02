package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IPostRepository;
import co.mobileaction.example.web.service.IPostQueueService;
import co.mobileaction.example.web.service.IPostService;
import co.mobileaction.example.web.service.IUserQueueService;
import co.mobileaction.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("api/admin")
@Secured(SecurityUtils.ROLE_ADMIN)
@RequiredArgsConstructor
public class AdminController
{
    private final IPostQueueService queueService;

    private final IUserQueueService userQueueService;

    private final IPostService postService;

    @PostMapping("queue/posts")
    public ResponseEntity<Boolean> createQueueRequests()
    {
        queueService.sendPostRequestForAllItems();

        return ResponseEntity.ok(true);
    }


    @PostMapping("/queue/users")
    public ResponseEntity<Boolean> createUserQueueRequest()
    {

        userQueueService.sendUserIdsForAllItems(postService.findDistinctUserIds());

        List<Long> usersIds = postService.findDistinctUserIds();
        for (Long userId : usersIds)
        System.out.println(userId);

        return ResponseEntity.ok(true);
    }





}
