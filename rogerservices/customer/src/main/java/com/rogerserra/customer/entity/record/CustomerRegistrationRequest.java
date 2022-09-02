package com.rogerserra.customer.entity.record;

// ventaja en lugar de utilizar una clase: con record ganamos inmutabilidad a Strings, equals etc
public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
