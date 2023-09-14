package co.mobileaction.example.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
    public static void main(String[] args)
    {
        SpringApplication.run(WebApplicationConfig.class, args);
    }
}
