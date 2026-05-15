package com.banking.cardservice.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("/api/v1/card")
@Produces(MediaType.APPLICATION_JSON)
public class CardResource {

    @GET
    @Path("/health")
    public Map<String, String> health() {
        return Map.of(
                "service", "card-service",
                "status", "UP"
        );
    }
}
