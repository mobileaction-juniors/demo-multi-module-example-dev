package co.mobileaction.example.worker.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig
{
    @Bean
    WebClient userClient(@Value("${external.usersBaseUrl:https://jsonplaceholder.typicode.com}") String base)
    {
        return WebClient.builder().baseUrl(base).build();
    }
}
