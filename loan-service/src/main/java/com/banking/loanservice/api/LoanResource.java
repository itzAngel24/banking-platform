package com.banking.loanservice.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("/api/v1/loan")
@Produces(MediaType.APPLICATION_JSON)
public class LoanResource {

    @GET
    @Path("/health")
    public Map<String, String> health() {
        return Map.of(
                "service", "loan-service",
                "status", "UP"
        );
    }
}
