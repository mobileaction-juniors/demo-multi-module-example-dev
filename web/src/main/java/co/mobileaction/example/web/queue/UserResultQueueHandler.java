package co.mobileaction.example.web.queue;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.service.IUserResultHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Yunus Gunay
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserResultQueueHandler
{
    private final AmqpTemplate userResultProblemQueueTemplate;

    private final IUserResultHandlerService userResultHandlerService;

    @RabbitListener(queues = "${messaging.queue.user.result}", containerFactory = "resultQueueListener")
    public void handleMessage(UserDto userDto)
    {
        try
        {
            userResultHandlerService.executeMessage(userDto);
        }
        catch (Exception e)
        {
            log.error("Could not handle result for userId: {}", userDto.id(), e);

            userResultProblemQueueTemplate.convertAndSend(userDto);
        }
    }
}
