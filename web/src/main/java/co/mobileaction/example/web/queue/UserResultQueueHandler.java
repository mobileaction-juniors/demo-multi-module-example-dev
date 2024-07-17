package co.mobileaction.example.web.queue;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.service.IUserResultHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Doga Elif Konuk
 * @date 17.07.2024
 * @time 15:15
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserResultQueueHandler {

    private final AmqpTemplate userResultProblemQueueTemplate;

    private final IUserResultHandlerService userResultHandlerService;

    @RabbitListener(queues = "ma-example-user-result-queue", containerFactory = "resultQueueListener")
    public void handleMessage(UserDto userResult)
    {
        try
        {
            userResultHandlerService.executeMessage(userResult);
        }
        catch (Exception e)
        {
            log.error("Could not handle result for userId: {}", userResult.getId(), e);

            userResultProblemQueueTemplate.convertAndSend(userResult);
        }
    }
}
