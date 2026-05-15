package com.banking.customerservice.domain;

import java.util.UUID;

public record Customer(UUID id, String documentNumber, String fullName, String kycStatus, String email, String phone) {
}
