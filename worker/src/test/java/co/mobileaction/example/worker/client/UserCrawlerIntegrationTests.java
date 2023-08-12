package co.mobileaction.example.worker.client;

import co.mobileaction.example.common.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserCrawlerIntegrationTests
{
    @Autowired
    private IUserCrawlerClient userCrawlerClient;

    private final Long USER_ID = 1L;

    @Test
    public void fetchUser_success()
    {
        UserDto dto = userCrawlerClient.fetchUser(USER_ID);

        assertThat(dto.getId()).isEqualTo(USER_ID);
        assertThat(dto.getName()).isEqualTo("Leanne Graham");
    }
}
