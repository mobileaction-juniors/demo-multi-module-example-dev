package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.util.SecurityUtils;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Secured(SecurityUtils.ROLE_USER)
@RequiredArgsConstructor
public class UserController
{
}
