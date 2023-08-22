package co.mobileaction.example.worker;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestOperations;

/**
 * @author sa
 * @date 17.05.2021
 * @time 14:51
 */
@SpringBootApplication
@ComponentScan("co.mobileaction.example")
public class WorkerAppConfig
{
    @Value("${messaging.consumer.initial-size}")
    private int CONSUMER_SIZE;

    @Value("${messaging.consumer.request.auto-start}")
    private boolean CONSUMER_REQUEST_AUTO_START;

    @Value("${messaging.consumer.request.max-size}")
    private int CONSUMER_REQUEST_MAX_SIZE;

    @Value("${messaging.queue.request.problem}")
    private String MESSAGING_REQUEST_PROBLEM_QUEUE;

    @Value("${messaging.queue.result}")
    private String MESSAGING_RESULT_QUEUE;

    @Value("${messaging.queue.result.user}")
    private String MESSAGING_RESULT_USER_QUEUE;

    @Value("${messaging.queue.request.user}")
    private String MESSAGING_USER_QUEUE;






    @Bean
    public AmqpTemplate userQueueTemplate(ConnectionFactory rabbitConnectionFactory,
                                          MessageConverter messageConverter)
    {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setRoutingKey(MESSAGING_USER_QUEUE);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public AmqpTemplate resultQueueTemplate(ConnectionFactory rabbitConnectionFactory,
                                             MessageConverter messageConverter)
    {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setRoutingKey(MESSAGING_RESULT_QUEUE);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public AmqpTemplate resultUserQueueTemplate(ConnectionFactory rabbitConnectionFactory,
                                             MessageConverter messageConverter)
    {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setRoutingKey(MESSAGING_RESULT_USER_QUEUE);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public AmqpTemplate requestProblemQueueTemplate(ConnectionFactory rabbitConnectionFactory,
                                                    MessageConverter messageConverter)
    {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setRoutingKey(MESSAGING_REQUEST_PROBLEM_QUEUE);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory requestQueueListener(ConnectionFactory connectionFactory,
                                                              MessageConverter messageConverter)
    {
        SimpleRabbitListenerContainerFactory container = new SimpleRabbitListenerContainerFactory();
        container.setConnectionFactory(connectionFactory);
        container.setConcurrentConsumers(CONSUMER_SIZE);
        container.setMaxConcurrentConsumers(CONSUMER_REQUEST_MAX_SIZE);
        container.setAutoStartup(CONSUMER_REQUEST_AUTO_START);
        container.setPrefetchCount(10);
        container.setMessageConverter(messageConverter);
        return container;
    }



    @Bean
    public RestOperations restTemplate(RestTemplateBuilder builder)
    {
        return builder.build();
    }

    public static void main(String[] args)
    {
        SpringApplication.run(WorkerAppConfig.class, args);
    }
}
