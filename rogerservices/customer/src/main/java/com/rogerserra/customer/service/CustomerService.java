package com.rogerserra.customer.service;

import com.rogerserra.amqp.producer.RabbitMQMessageProducer;
import com.rogerserra.clients.fraud.FraudCheckResponse;
import com.rogerserra.clients.fraud.FraudClient;
import com.rogerserra.clients.notification.NotificationClient;
import com.rogerserra.clients.notification.NotificationRequest;
import com.rogerserra.customer.entity.Customer;
import com.rogerserra.customer.entity.record.CustomerRegistrationRequest;
import com.rogerserra.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service //spring lo inicializa por nosotros como bean
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.saveAndFlush(customer); // so we can have access to the customer id
        // sin save and flush el ID VA A SER NULO
        // todo: check if fraudster

        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, nice to meet you!", customer.getFirstName())
        );

        // es lo que haciamos en el main de Notification con el CommandlineRunner
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");
        /* ya no hace falta, ahora enviamos a una cola de mensajeria
        notificationClient.sendNotification(
                notificationRequest
        );
        */


    }
}
