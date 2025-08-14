package co.mobileaction.example.web;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitQueueConfig {

    @Value("${messaging.queue.result.problem}")
    private String resultProblemQueue;

    @Value("${messaging.queue.result}")
    private String resultQueue;

    @Value("${messaging.queue.request}")
    private String requestQueue;

    @Value("${messaging.queue.user.result.problem}")
    private String userResultProblemQueue;

    @Value("${messaging.queue.user.result}")
    private String resultUserQueue;

    @Value("${messaging.queue.user.request}")
    private String userRequestQueue;

    @Bean
    public Queue resultProblemQueue() {
        return new Queue(resultProblemQueue, true); // durable queue
    }

    @Bean
    public Queue resultQueue() {
        return new Queue(resultQueue, true);
    }

    @Bean
    public Queue requestQueue()
    {
        return new Queue(requestQueue, true);
    }

    @Bean
    public Queue userResultProblemQueue() {
        return new Queue(userResultProblemQueue, true);
    }

    @Bean
    public Queue userRequestQueue() {
        return new Queue(userRequestQueue, true);
    }
}
