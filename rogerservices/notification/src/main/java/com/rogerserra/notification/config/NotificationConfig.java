package com.rogerserra.notification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Configuration: It is a class-level annotation. The class annotated with @Configuration used by Spring Containers as a source of bean definitions.
public class NotificationConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange; // su valor viene del application.yml, concretamente de rabbitmq:exchanges:internal

    @Value("${rabbitmq.queue.notification}")
    private String notificactionQueue;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;

    // Hay exchange, que es donde el microservicio productor entrega el mensaje
    // hay cola de mensajerias, que apuntan al topic exchange
    // hay bindings, que unen el exchange con las distintas colas de mensajeria

    @Bean
    public TopicExchange internalTopicExchange(){
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue notificationQueue(){
        return new Queue(this.notificactionQueue);
    }

    @Bean
    public Binding internalToNotificationBinding(){
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(this.internalNotificationRoutingKey);
    }

    public String getInternalExchange() {
        return internalExchange;
    }

    public String getNotificactionQueue() {
        return notificactionQueue;
    }

    public String getInternalNotificationRoutingKey() {
        return internalNotificationRoutingKey;
    }
}
