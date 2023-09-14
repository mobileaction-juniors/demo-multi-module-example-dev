package co.mobileaction.example.web;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig
{
    @Value("${messaging.consumer.initial-size}")
    private int CONSUMER_SIZE;

    @Value("${messaging.consumer.result.auto-start}")
    private boolean CONSUMER_RESULT_AUTO_START;

    @Value("${messaging.consumer.result.max-size}")
    private int CONSUMER_RESULT_MAX_SIZE;

    @Value("${messaging.consumer.interval}")
    private Long INTERVAL_IN_MS;

    @Value("${messaging.server.routing-key}")
    private String MESSAGING_SERVER_ROUTING_KEY;

    @Value("${messaging.queue.result.problem}")
    private String MESSAGING_RESULT_PROBLEM_QUEUE;

    @Value("${messaging.queue.request}")
    private String MESSAGING_REQUEST_QUEUE;

    @Bean
    Queue queue() {
        return new Queue(MESSAGING_REQUEST_QUEUE, false);
    }

    @Bean
    Queue queue_problem() {
        return new Queue(MESSAGING_RESULT_PROBLEM_QUEUE, false);
    }

    @Bean
    TopicExchange exchange()
    {
        return new TopicExchange("mobileaction");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange)
    {
        return BindingBuilder.bind(queue).to(exchange).with(String.format("%s.%s", MESSAGING_REQUEST_QUEUE, MESSAGING_SERVER_ROUTING_KEY));
    }

    @Bean
    Binding binding_problem(Queue queue_problem, TopicExchange exchange)
    {
        return BindingBuilder.bind(queue_problem).to(exchange).with(String.format("%s.%s", MESSAGING_RESULT_PROBLEM_QUEUE, MESSAGING_SERVER_ROUTING_KEY));
    }

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
}
