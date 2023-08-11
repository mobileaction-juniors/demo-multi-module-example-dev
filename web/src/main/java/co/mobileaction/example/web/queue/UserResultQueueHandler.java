package co.mobileaction.example.web.queue;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.service.IUserResultHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor

public class UserResultQueueHandler
{
    private final AmqpTemplate userResultProblemQueueTemplate;
    private final IUserResultHandlerService userResultHandlerService;

    @RabbitListener(queues = "${messaging.queue.user.result}", containerFactory = "resultQueueListener")
    public void handleMessage(UserDto result)
    {
        try
        {
            userResultHandlerService.executeMessage(result);
        }
        catch (Exception e)
        {
            log.error("Could not handle result for userId: {}", result.getId(), e);

            userResultProblemQueueTemplate.convertAndSend(result);
        }
    }
}
