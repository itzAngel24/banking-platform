package com.banking.reportingservice.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("/api/v1/reporting")
@Produces(MediaType.APPLICATION_JSON)
public class ReportingResource {

    @GET
    @Path("/health")
    public Map<String, String> health() {
        return Map.of(
                "service", "reporting-service",
                "status", "UP"
        );
    }
}
