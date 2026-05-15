package com.banking.accountservice.api;

import com.banking.accountservice.application.AccountService;
import com.banking.accountservice.domain.Account;
import com.banking.accountservice.domain.CreateAccountRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountService accountService;

    @POST
    public Account create(CreateAccountRequest request) {
        return accountService.create(request);
    }
}
