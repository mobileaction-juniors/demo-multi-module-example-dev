package co.mobileaction.example.web.service;

import co.mobileaction.example.dto.QueueNames;
import co.mobileaction.example.dto.UserCrawlRequestDto;
import co.mobileaction.example.web.repository.IPostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.LongStream;

@Service
public class PostQueueService implements IPostQueueService {

    private static final Logger log = LoggerFactory.getLogger(PostQueueService.class);

    private final AmqpTemplate amqpTemplate;   
    private final IPostRepository postRepository;

    public PostQueueService(@Qualifier("requestQueueTemplate") AmqpTemplate amqpTemplate,
                            IPostRepository postRepository) {
        this.amqpTemplate = amqpTemplate;
        this.postRepository = postRepository;
    }

    @Override
    public void sendPostRequestForAllItems() 
    {
        LongStream.rangeClosed(1, 100).forEach(id -> {
            amqpTemplate.convertAndSend(QueueNames.CRAWL_USER_REQUEST, id);
            log.info("Enqueued crawl request for userId={}", id);
        });
    }

    @Override
    public int enqueueDistinctUserIdsForCrawl() 
    {
        var userIds = postRepository.findDistinctUserIds();
        userIds.stream()
                .filter(Objects::nonNull)
                .distinct()
                .forEach(id -> {
                    amqpTemplate.convertAndSend(QueueNames.CRAWL_USER_REQUEST, new UserCrawlRequestDto(id));
                    log.info("Enqueued crawl request for userId={}", id);
                });
        return userIds.size();
    }
}
