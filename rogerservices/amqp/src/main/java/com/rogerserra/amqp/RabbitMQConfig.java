package com.rogerserra.amqp;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Tags the class as a source of bean definitions for the application context.
@AllArgsConstructor
public class RabbitMQConfig {

    private final ConnectionFactory connectionFactory;

    // allows us to send the messages to the queue
    @Bean
    public AmqpTemplate amqpTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory); // implementa AmqpTemplate entre otras
        rabbitTemplate.setMessageConverter(jacksonConverter()); // enviamos mensajes como json
        return rabbitTemplate;
    }; //https://docs.spring.io/spring-amqp/api/org/springframework/amqp/core/AmqpTemplate.html

    // allows us to receive the messages from the queue
    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(){
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jacksonConverter());
        return factory;
    }

    @Bean
    public MessageConverter jacksonConverter(){
        MessageConverter jackson2JsonMessageConverter =
                new Jackson2JsonMessageConverter(); // podemos pasar un objectmapper por parametro
        return jackson2JsonMessageConverter;
    }
}
