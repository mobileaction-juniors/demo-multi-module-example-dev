package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.ICityService;
import co.mobileaction.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@Secured(SecurityUtils.ROLE_USER)
@RequestMapping("api/cities")
@RequiredArgsConstructor
public class CityController
{
    private final ICityService cityService;

    @GetMapping
    public ResponseEntity<Void> fetchAndSaveCity(@RequestParam String name)
    {
        cityService.fetchAndSaveCity(name);
        return ResponseEntity.ok().build();
    }
}
