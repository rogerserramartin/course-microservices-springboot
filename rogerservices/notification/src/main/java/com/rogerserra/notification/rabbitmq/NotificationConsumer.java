package com.rogerserra.notification.rabbitmq;

import com.rogerserra.clients.notification.NotificationRequest;
import com.rogerserra.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component // tambien podria ser @Service
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}") // se parece a @Value en Config que pilla del .yml
    public void consumer(NotificationRequest notificationRequest){
        log.info("Consumed {} from queue", notificationRequest);
        notificationService.send(notificationRequest);

    }
}
