package com.banking.customerservice.application;

import com.banking.customerservice.domain.CreateCustomerRequest;
import com.banking.customerservice.domain.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    public Customer create(CreateCustomerRequest request) {
        Customer customer = new Customer(
                UUID.randomUUID(),
                request.documentNumber(),
                request.fullName(),
                "PENDING",
                request.email(),
                request.phone()
        );
        return customerRepository.save(customer);
    }
}
