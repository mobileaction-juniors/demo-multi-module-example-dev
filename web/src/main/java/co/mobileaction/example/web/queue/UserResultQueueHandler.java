package co.mobileaction.example.web.queue;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserResultQueueHandler {
    private final IUserService userService;

    @Value("${messaging.queue.user-result}")
    private String userResultQueueName;

    @RabbitListener(queues = "${messaging.queue.user-result}")
    public void handleMessage(UserDto userDto) {
        try {
            if (userDto == null) {
                log.error("Received null userDto from worker. Skipping save.");
                return;
            }
            userService.saveUser(convertFrom(userDto));
        } catch (Exception e) {
            log.error("Could not handle user result for userId: {}", userDto != null ? userDto.getId() : null, e);
        }
    }

    private User convertFrom(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .build();
    }
}