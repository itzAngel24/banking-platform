package com.banking.walletservice.api;

import com.banking.walletservice.application.WalletService;
import com.banking.walletservice.domain.WalletPaymentRequest;
import com.banking.walletservice.domain.WalletPaymentResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/wallet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WalletResource {

    @Inject
    WalletService walletService;

    @POST
    @Path("/payments")
    public WalletPaymentResponse send(WalletPaymentRequest request) {
        return walletService.send(request);
    }
}
