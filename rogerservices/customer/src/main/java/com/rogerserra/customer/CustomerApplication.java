package com.rogerserra.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.rogerserra.customer",
                "com.rogerserra.amqp",
        }

)
@EnableEurekaClient // el main de nuestro microservicio eureka-cliente tiene el enableeurekaserver
@EnableFeignClients(
        basePackages = "com.rogerserra.clients"
)
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
