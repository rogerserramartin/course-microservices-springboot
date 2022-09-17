package com.rogerserra.notification;

import com.rogerserra.amqp.producer.RabbitMQMessageProducer;
import com.rogerserra.notification.config.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//  Control + SHIFT + F12 -> full screen

@SpringBootApplication(
        scanBasePackages = {
                "com.rogerserra.notification",
                "com.rogerserra.amqp"
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }


    /* pruebecillas
    // es para inyectar el producer(RabbitMQMessageProducer.java, el main de ese submodulo), para eso lo de scanBasePackages. Además, el POM tiene la dependencia de com.rogerserra.amqp, por eso lo pilla
    @Bean
    CommandLineRunner commandLineRunner(
            RabbitMQMessageProducer producer,
            NotificationConfig notificationConfig){
        return args -> {
            producer.publish(
                    "Pikachu",
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKey());
        };
    }
    */

}
