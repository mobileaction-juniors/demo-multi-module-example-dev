package co.mobileaction.example.web.controller;

import co.mobileaction.example.web.service.UserQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserCrawlController {

    private final UserQueueService userQueueService;

    //api/users/crawl endpoint to trigger the crawling process for distinct users
    @PostMapping("/crawl")
    public ResponseEntity<Void> crawlDistinctUsers() {
        //trigger the crawling process for distinct users
        userQueueService.sendCrawlRequestForDistinctUsers();

        //returning 202 accepted as the crawling process is asynchronous and will be handled in the background
        return ResponseEntity.accepted().build();
    }
}
