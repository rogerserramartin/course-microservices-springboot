package com.rogerserra.customer.service;

import com.rogerserra.customer.entity.Customer;
import com.rogerserra.customer.entity.record.CustomerRegistrationRequest;
import com.rogerserra.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service //spring lo inicializa por nosotros como bean
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.save(customer);
    }
}
