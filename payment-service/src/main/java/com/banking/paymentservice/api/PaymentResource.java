package com.banking.paymentservice.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("/api/v1/payment")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResource {

    @GET
    @Path("/health")
    public Map<String, String> health() {
        return Map.of(
                "service", "payment-service",
                "status", "UP"
        );
    }
}
