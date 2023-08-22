package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.util.SecurityUtils;
import co.mobileaction.example.web.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import co.mobileaction.example.web.model.User;

import java.util.List;

@RestController
@Secured(SecurityUtils.ROLE_USER)
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping()
    public ResponseEntity<List<Long>> getAllUsers()
    {
        return ResponseEntity.ok(userService.getAllDistinctIds());
    }



}
