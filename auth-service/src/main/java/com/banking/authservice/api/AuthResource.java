package com.banking.authservice.api;

import com.banking.authservice.application.AuthService;
import com.banking.authservice.domain.LoginRequest;
import com.banking.authservice.domain.TokenResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    public TokenResponse login(LoginRequest request) {
        return authService.login(request);
    }
}
