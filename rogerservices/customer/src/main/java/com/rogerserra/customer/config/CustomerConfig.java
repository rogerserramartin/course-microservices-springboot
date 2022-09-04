package com.rogerserra.customer.config;

import com.rogerserra.clients.notification.NotificationClient;
import com.rogerserra.clients.notification.NotificationRequest;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig { // para enviar http requests

    @Bean
    @LoadBalanced // sin esto, el MS customer no sabe como contactar con el fraud. La request puede ir a cualquier instancia con esta anotacion
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
