package co.mobileaction.example.web.queue;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.service.IUserResultHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author elif
 * @date 05.07.2023
 * @time 14.38
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserResultQueueHandler
{
    private final AmqpTemplate userResultProblemQueueTemplate;
    private final IUserResultHandlerService resultHandlerService;

    @RabbitListener(queues = "${messaging.queue.user.result}", containerFactory = "resultQueueListener")
    public void handleMessage(UserDto result)
    {
        try
        {
            resultHandlerService.executeMessage(result);
        }
        catch (Exception e)
        {
            log.error("Could not handle result for postId: {} because {}", result.getId(), e.getMessage());

            userResultProblemQueueTemplate.convertAndSend(result);
        }
    }
}
