package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.service.IUserService;
import co.mobileaction.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
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
public class UserController {

    private final IUserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@PageableDefault(size = 10)
                                                  @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable)
    {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

}
