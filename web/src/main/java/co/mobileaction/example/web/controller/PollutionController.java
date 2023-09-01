package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.dto.AnswerDto;
import co.mobileaction.example.web.service.IPollutionService;
import co.mobileaction.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@Secured(SecurityUtils.ROLE_USER)
@RequestMapping("api/pollutions")
@RequiredArgsConstructor
public class PollutionController
{
    private final IPollutionService pollutionService;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping
    public ResponseEntity<AnswerDto> getResults(@RequestParam String cityName,
                                                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate start,
                                                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate end)
    {
        if(start == null || end == null)
        {
            end = LocalDate.now();
            start = end.minusDays(6);
        }

        return ResponseEntity.ok(pollutionService.getResults(cityName, start, end));
    }
}
