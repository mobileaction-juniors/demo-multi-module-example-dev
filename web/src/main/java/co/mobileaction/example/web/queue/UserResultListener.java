package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.QueueNames;
import co.mobileaction.example.common.dto.UserCrawlResultDto;
import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserResultListener
{

    private final IUserRepository userRepository;

    @Transactional
    @RabbitListener(queues = QueueNames.CRAWL_USER_RESULT)
    public void onUserResult(UserCrawlResultDto dto) 
    {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        userRepository.save(user);
    }
}
