package co.mobileaction.example.web;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author sa
 * @date 17.05.2021
 * @time 13:53
 */
@SpringBootApplication
@ComponentScan("co.mobileaction.example")
public class WebApplicationConfig
{
    @Value("${messaging.consumer.initial-size}")
    private int CONSUMER_SIZE;

    @Value("${messaging.consumer.result.auto-start}")
    private boolean CONSUMER_RESULT_AUTO_START;

    @Value("${messaging.consumer.result.max-size}")
    private int CONSUMER_RESULT_MAX_SIZE;

    @Value("${messaging.consumer.interval}")
    private Long INTERVAL_IN_MS;

    @Value("${messaging.queue.result.problem}")
    private String MESSAGING_RESULT_PROBLEM_QUEUE;

    @Value("${messaging.queue.request}")
    private String MESSAGING_REQUEST_QUEUE;

    @Value("${messaging.queue.request.user}")
    private String MESSAGING_USER_REQUEST_QUEUE;


    @Bean
    public AmqpTemplate resultProblemQueueTemplate(ConnectionFactory rabbitConnectionFactory,
                                                   MessageConverter messageConverter)
    {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setRoutingKey(MESSAGING_RESULT_PROBLEM_QUEUE);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public AmqpTemplate userRequestQueueTemplate(ConnectionFactory rabbitConnectionFactory,
                                                   MessageConverter messageConverter)
    {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setRoutingKey(MESSAGING_USER_REQUEST_QUEUE);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public AmqpTemplate requestQueueTemplate(ConnectionFactory rabbitConnectionFactory,
                                             MessageConverter messageConverter)
    {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setRoutingKey(MESSAGING_REQUEST_QUEUE);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory resultQueueListener(ConnectionFactory connectionFactory,
                                                                    MessageConverter messageConverter)
    {
        SimpleRabbitListenerContainerFactory container = new SimpleRabbitListenerContainerFactory();
        container.setConnectionFactory(connectionFactory);
        container.setMessageConverter(messageConverter);
        container.setConcurrentConsumers(CONSUMER_SIZE);
        container.setStartConsumerMinInterval(INTERVAL_IN_MS);
        container.setStopConsumerMinInterval(INTERVAL_IN_MS);
        container.setPrefetchCount(10);
        container.setMaxConcurrentConsumers(CONSUMER_RESULT_MAX_SIZE);
        container.setAutoStartup(CONSUMER_RESULT_AUTO_START);
        return container;
    }


    public static void main(String[] args)
    {
        SpringApplication.run(WebApplicationConfig.class, args);
    }
}
