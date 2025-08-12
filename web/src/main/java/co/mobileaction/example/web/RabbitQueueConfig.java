package co.mobileaction.example.web;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitQueueConfig {

    @Value("${messaging.queue.result.problem}")
    private String resultProblemQueue;

    @Value("${messaging.queue.result}")
    private String requestQueue;

    @Bean
    public Queue resultProblemQueue() {
        return new Queue(resultProblemQueue, true); // durable queue
    }

    @Bean
    public Queue requestQueue() {
        return new Queue(requestQueue, true);
    }
}
