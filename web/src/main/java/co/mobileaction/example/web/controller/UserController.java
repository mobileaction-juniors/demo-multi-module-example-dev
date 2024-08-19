package co.mobileaction.example.web.controller;
import co.mobileaction.example.web.service.IUserQueueService;
import co.mobileaction.example.web.service.IUserService;
import co.mobileaction.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Secured(SecurityUtils.ROLE_USER)
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController
{
    private final IUserService service;
    private final IUserQueueService queueService;

    @GetMapping("/ids")
    public ResponseEntity<List<Long>> getUsers()
    {
        queueService.sendRequestForItems();
        return ResponseEntity.ok(service.getDistinctIds());
    }


}