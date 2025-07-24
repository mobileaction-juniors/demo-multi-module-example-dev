package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.service.IUserQueueService;
import co.mobileaction.example.web.service.IUserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;     

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
            return ResponseEntity.ok(userService.findById(userId));
    }

    private final IUserQueueService userQueueService;

    @PostMapping("/crawl-users")
    public ResponseEntity<String> crawlUsers() {
        userQueueService.sendUserRequestForAllItems();
        return ResponseEntity.ok("User crawl requests sent.");
    }
}
