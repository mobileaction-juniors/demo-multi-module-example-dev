package co.mobileaction.example.web.queue;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import org.springframework.stereotype.Component;

@Component
public class ResultQueueHandler {

    private final IUserRepository userRepository;

    public ResultQueueHandler(IUserRepository userRepository) 
    {
        this.userRepository = userRepository;
    }

    public void onUserCrawlResult(User user) 
    {
        userRepository.save(user);
    }
}
