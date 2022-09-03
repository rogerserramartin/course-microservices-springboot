package com.rogerserra.customer.service;

import com.rogerserra.clients.fraud.FraudCheckResponse;
import com.rogerserra.clients.fraud.FraudClient;
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
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

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

        // todo: send notification
    }
}
