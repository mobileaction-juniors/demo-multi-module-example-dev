package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.QueueNames;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

@Configuration
@EnableRabbit
public class RabbitConfig
{
    @Bean Queue crawlUserRequestQueue() { return new Queue(QueueNames.CRAWL_USER_REQUEST, true); }
    @Bean Queue crawlUserResultQueue()  { return new Queue(QueueNames.CRAWL_USER_RESULT, true); }
}
