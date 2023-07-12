package co.mobileaction.example.worker.client;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.UserDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author sa
 * @date 18.05.2021
 * @time 10:44
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Disabled
public class CrawlerClientIntegrationTests
{
    @Autowired
    private ICrawlerClient crawlerClient;

    @Test
    public void fetchPost_success()
    {
        PostDto dto = crawlerClient.fetchPost(1L);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getUserId()).isEqualTo(1L);
    }

    @Test
    public void fetchUser_success()
    {
        UserDto dto = crawlerClient.fetchUser(1L);

        assertThat(dto.getId()).isEqualTo(1L);
    }
}
