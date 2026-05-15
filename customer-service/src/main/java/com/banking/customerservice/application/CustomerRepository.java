package com.banking.customerservice.application;

import com.banking.customerservice.domain.Customer;

public interface CustomerRepository {
    Customer save(Customer customer);
}
