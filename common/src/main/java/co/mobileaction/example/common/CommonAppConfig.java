package co.mobileaction.example.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author sa
 * @date 17.05.2021
 * @time 12:37
 */
//Spring Boot will automatically load the properties in an application.properties file for all profiles,
// and the ones in profile-specific .properties files only for the specified profile.
@SpringBootConfiguration
public class  CommonAppConfig
{
    @Value("${messaging.server.url}")
    private String MESSAGING_SERVER_URL;

    @Value("${messaging.queue.result.problem}")
    private String MESSAGING_RESULT_PROBLEM_QUEUE;

    @Value("${messaging.queue.request}")
    private String MESSAGING_REQUEST_QUEUE;

    @Value("${messaging.queue.user.result.problem}")
    private String MESSAGING_USER_RESULT_PROBLEM_QUEUE;

    @Value("${messaging.queue.user.request}")
    private String MESSAGING_USER_REQUEST_QUEUE;

    @Value("${messaging.queue.user.result}")
    private String MESSAGING_USER_RESULT_QUEUE;

    @Value("${messaging.queue.result}")
    private String MESSAGING_RESULT_QUEUE;

    @Bean
    public ConnectionFactory rabbitConnectionFactory()
    {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUri(MESSAGING_SERVER_URL);
        return factory;
    }

    @Bean
    public MessageConverter messageConverter()
    {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return  new RabbitAdmin(rabbitConnectionFactory());
    }

    @Bean
    public Queue resultProblemQueue() {
        return new Queue(MESSAGING_RESULT_PROBLEM_QUEUE, true);
    }

    @Bean
    public Queue requestQueue() {
        return new Queue(MESSAGING_REQUEST_QUEUE, true);
    }

    @Bean
    public Queue userResultProblemQueue() {
        return new Queue(MESSAGING_USER_RESULT_PROBLEM_QUEUE, true);
    }

    @Bean
    public Queue userRequestQueue() {
        return new Queue(MESSAGING_USER_REQUEST_QUEUE, true);
    }

    @Bean
    public Queue resultQueue() {
        return new Queue(MESSAGING_RESULT_QUEUE, true);
    }

    @Bean
    public Queue userResultQueue() {
        return new Queue(MESSAGING_USER_RESULT_QUEUE, true);
    }
}
