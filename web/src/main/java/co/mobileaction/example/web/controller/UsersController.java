package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.model.dto.CreateUserCmd;
import co.mobileaction.example.web.model.User;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Secured(SecurityUtils.ROLE_USER)
public class UsersController
{
    private final IUserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers(
            @PageableDefault(size = 20)
            @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable)
    {
        return ResponseEntity.ok(userService.findUsers(pageable));
    }

    @PostMapping("")
    public ResponseEntity<User> createNewUser(CreateUserCmd createUserCmd)
    {
        return ResponseEntity.ok(userService.saveUser(createUserCmd));
    }
}
