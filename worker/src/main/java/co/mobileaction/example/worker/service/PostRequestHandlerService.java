package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.worker.client.ICrawlerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * @author sa
 * @date 17.05.2021
 * @time 16:55
 */
@Service
@RequiredArgsConstructor
public class PostRequestHandlerService implements IPostRequestHandlerService
{
    private final AmqpTemplate resultQueueTemplate;

    private final ICrawlerClient crawlerClient;

    @Override
    public void executeMessage(QueueRequestDto request)
    {
        PostDto post = crawlerClient.fetchPost(request.getPostId());

        resultQueueTemplate.convertAndSend(post);
    }


}
