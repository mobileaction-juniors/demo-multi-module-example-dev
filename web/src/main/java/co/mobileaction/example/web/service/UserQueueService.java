package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserCrawlRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import co.mobileaction.example.web.repository.IPostRepository;

import java.util.List;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:57
 */
@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService
{
    private final AmqpTemplate userRequestQueueTemplate;
    private final IPostRepository postRepository;

    @Override
    @Transactional
    public void sendUserRequestForAllItems()
    {
        List<Long> userIds = postRepository.findDistinctUserIds();
        userIds.stream()
                .map(id -> UserCrawlRequestDto.builder().id(id).build())
                .forEach(requestDto -> userRequestQueueTemplate.convertAndSend(requestDto));
    }

}
