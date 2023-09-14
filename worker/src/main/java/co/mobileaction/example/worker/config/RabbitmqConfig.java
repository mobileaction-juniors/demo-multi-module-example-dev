package co.mobileaction.example.worker.config;

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

    @Value("${messaging.consumer.request.auto-start}")
    private boolean CONSUMER_REQUEST_AUTO_START;

    @Value("${messaging.consumer.request.max-size}")
    private int CONSUMER_REQUEST_MAX_SIZE;

    @Value("${messaging.server.routing-key}")
    private String MESSAGING_SERVER_ROUTING_KEY;

    @Value("${messaging.queue.request.problem}")
    private String MESSAGING_REQUEST_PROBLEM_QUEUE;

    @Value("${messaging.queue.result}")
    private String MESSAGING_RESULT_QUEUE;

    @Bean
    Queue queue() {
        return new Queue(MESSAGING_RESULT_QUEUE, false);
    }

    @Bean
    Queue queue_problem() {
        return new Queue(MESSAGING_REQUEST_PROBLEM_QUEUE, false);
    }

    @Bean
    TopicExchange exchange()
    {
        return new TopicExchange("mobileaction");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange)
    {
        return BindingBuilder.bind(queue).to(exchange).with(String.format("%s.%s", MESSAGING_RESULT_QUEUE, MESSAGING_SERVER_ROUTING_KEY));
    }

    @Bean
    Binding binding_problem(Queue queue_problem, TopicExchange exchange)
    {
        return BindingBuilder.bind(queue_problem).to(exchange).with(String.format("%s.%s", MESSAGING_REQUEST_PROBLEM_QUEUE, MESSAGING_SERVER_ROUTING_KEY));
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
}
