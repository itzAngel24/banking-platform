package com.banking.customerservice.domain;

public record CreateCustomerRequest(String documentNumber, String fullName, String email, String phone) {
}
