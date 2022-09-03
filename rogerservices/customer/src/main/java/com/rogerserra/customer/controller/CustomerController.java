package com.rogerserra.customer.controller;

import com.rogerserra.customer.entity.record.CustomerRegistrationRequest;
import com.rogerserra.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // for logging things
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
        log.info("new customer registration{}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }
}
