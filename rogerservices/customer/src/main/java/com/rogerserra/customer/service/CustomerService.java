package com.rogerserra.customer.service;

import com.rogerserra.customer.entity.Customer;
import com.rogerserra.customer.entity.record.CustomerRegistrationRequest;
import com.rogerserra.customer.entity.record.FraudCheckResponse;
import com.rogerserra.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service //spring lo inicializa por nosotros como bean
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

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
        // esto envia el ID a la ruta especificada, y alli el controlador de Fraud ya se encarga de enviar al servicio
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()

        );
        // FRAUD EN MAYUSCULA es porque es el nombre de la aplicacion en localhost:8761, en la consola de eureka server, es en MAYUSCULA
        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        // todo: send notification
    }
}
