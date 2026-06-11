package co.mobileaction.example.web.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.web.repository.IPostRepository;

@Service
@RequiredArgsConstructor
public class UserQueueService{
    private final IPostRepository postRepository;
    private final AmqpTemplate userRequestQueueTemplate;

    public void sendCrawlRequestForDistinctUsers() {
        //fetching distinct userIds from the database
        List<Long> distinctUserIds = postRepository.findDistinctUserIds();

        //sending a crawl request for each distinct userId to the worker module via the request queue
        distinctUserIds.stream()
                .map(id -> new UserQueueRequestDto(id))
                .forEach(userRequestQueueTemplate::convertAndSend);
    }
}
