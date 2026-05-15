package com.banking.transferservice.api;

import com.banking.transferservice.application.TransferService;
import com.banking.transferservice.domain.InternalTransferRequest;
import com.banking.transferservice.domain.TransferResult;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/transfers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransferResource {

    @Inject
    TransferService transferService;

    @POST
    @Path("/internal")
    public TransferResult internalTransfer(InternalTransferRequest request) {
        return transferService.transferInternal(request);
    }
}
