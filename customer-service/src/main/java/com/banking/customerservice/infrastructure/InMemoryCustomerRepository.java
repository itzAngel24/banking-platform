package com.banking.customerservice.infrastructure;

import com.banking.customerservice.application.CustomerRepository;
import com.banking.customerservice.domain.Customer;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<UUID, Customer> store = new ConcurrentHashMap<>();

    @Override
    public Customer save(Customer customer) {
        store.put(customer.id(), customer);
        return customer;
    }
}
