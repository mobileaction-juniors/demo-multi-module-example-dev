package co.mobileaction.example.web;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitStartupVerifier {

    public RabbitStartupVerifier(AmqpAdmin amqpAdmin, @Value("${messaging.queue.result}") String queueName) {
        if (amqpAdmin.getQueueProperties(queueName) != null && !amqpAdmin.getQueueProperties(queueName).isEmpty()) {
            System.out.println("Queue exists: " + queueName);
        } else {
            System.out.println("Queue DOES NOT exist: " + queueName);
        }
    }
}
