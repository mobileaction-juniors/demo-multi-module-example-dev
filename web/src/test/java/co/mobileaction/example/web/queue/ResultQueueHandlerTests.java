package co.mobileaction.example.web.queue;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ResultQueueHandlerTests {

    IUserRepository userRepository = mock(IUserRepository.class);

    ResultQueueHandler handler = new ResultQueueHandler(userRepository);

    @Test
    void onUserCrawlResult_persistsUserToDb() 
    {
        User payload = new User(); 

        handler.onUserCrawlResult(payload);

        verify(userRepository, times(1)).save(payload);
        verifyNoMoreInteractions(userRepository);
    }
}
