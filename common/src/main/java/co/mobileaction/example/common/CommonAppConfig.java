package co.mobileaction.example.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
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
public class CommonAppConfig
{
    @Value("${messaging.server.url}")
    private String MESSAGING_SERVER_URL;

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
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return new Jackson2JsonMessageConverter(mapper);
    }
}
