package co.mobileaction.example.web.queue;

import co.mobileaction.example.dto.QueueNames;
import co.mobileaction.example.dto.UserCrawlResultDto;
import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserResultListener {

    private final IUserRepository userRepository;

    @Transactional
    @RabbitListener(queues = QueueNames.CRAWL_USER_RESULT)
    public void onUserResult(UserCrawlResultDto dto) 
    {
        User u = new User();
        u.setId(dto.getId());
        u.setName(dto.getName());
        u.setUsername(dto.getUsername());
        userRepository.save(u);
    }
}
