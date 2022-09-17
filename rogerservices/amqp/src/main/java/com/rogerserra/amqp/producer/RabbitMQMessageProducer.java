package com.rogerserra.amqp.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageProducer {

    private final AmqpTemplate amqpTemplate;

    public void publish(Object payload, String exchange, String routingKey){ // es Object porque queremos enviar CUALQUIER payload
        log.info("Publishing to {} using routing key {}. Payload: {} ", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to {} using routing key {}. Payload: {} ", exchange, routingKey, payload);


    }
}
