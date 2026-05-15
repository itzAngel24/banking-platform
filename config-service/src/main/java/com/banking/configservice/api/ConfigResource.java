package com.banking.configservice.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("/api/v1/config")
@Produces(MediaType.APPLICATION_JSON)
public class ConfigResource {

    @GET
    @Path("/health")
    public Map<String, String> health() {
        return Map.of(
                "service", "config-service",
                "status", "UP"
        );
    }
}
