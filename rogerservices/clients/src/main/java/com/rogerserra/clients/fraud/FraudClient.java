package com.rogerserra.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// esto es mejor. This interface will target FraudController.class on Fraud microservice
@FeignClient(value = "fraud")
public interface FraudClient {
    @GetMapping(path = "api/v1/fraud-check/{customerId}")
    FraudCheckResponse isFraudster(
            @PathVariable(name = "customerId") Integer customerId);
}
/*
// son lo mismo estos atajos, pero el espaciadora se ve mas bonito
// control + P
// control + espacio
// el name es la ruta del MS de fraud RequestMapping
@FeignClient(
    value = "fraud",
    name = "api/v1/fraud-check"
)
public interface FraudClient {
}
*/


