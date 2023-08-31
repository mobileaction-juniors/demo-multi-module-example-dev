package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.dto.AnswerDto;
import co.mobileaction.example.web.repository.IPollutionRepository;
import co.mobileaction.example.web.service.IPollutionService;
import co.mobileaction.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@Secured(SecurityUtils.ROLE_USER)
@RequestMapping("api/pollutions")
@RequiredArgsConstructor
public class PollutionController
{
    private final IPollutionService pollutionService;
    private static final Long SECONDS_IN_ONE_DAY = 86400L;

    @GetMapping
    public ResponseEntity<AnswerDto> getResults(@RequestParam String cityName,
                                                @RequestParam(required = false) Long start, @RequestParam(required = false) Long end)
    {
        if(start == null && end == null)
        {
            end = Instant.now().getEpochSecond();
            start = end - (7 * SECONDS_IN_ONE_DAY);
        }
        return ResponseEntity.ok(pollutionService.getResults(cityName, start, end));
    }
}
