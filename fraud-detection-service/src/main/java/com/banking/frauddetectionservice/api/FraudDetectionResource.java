package com.banking.frauddetectionservice.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("/api/v1/fraud-detection")
@Produces(MediaType.APPLICATION_JSON)
public class FraudDetectionResource {

    @GET
    @Path("/health")
    public Map<String, String> health() {
        return Map.of(
                "service", "fraud-detection-service",
                "status", "UP"
        );
    }
}
