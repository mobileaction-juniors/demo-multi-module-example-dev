package co.mobileaction.example.web.queue;

import co.mobileaction.example.common.dto.UserCrawlRequestDto;
import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.service.IUserResultHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:38
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserResultQueueHandler
{
    private final IUserResultHandlerService userResultHandlerService;

    @RabbitListener(queues = "${messaging.queue.userResult}", containerFactory = "resultQueueListener")
    public void handleMessage(UserDto userDto) {
        try {
            userResultHandlerService.executeMessage(userDto);
        } catch (Exception e) {
            log.error("Could not handle user result for userId: {}", userDto.getId(), e);
        }
    }
}
