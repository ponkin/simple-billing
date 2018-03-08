package ru.ponkin.billing.resources;

import com.google.inject.Inject;
import ru.ponkin.billing.proto.Transfer;
import ru.ponkin.billing.services.TransferService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * @author Alexey Ponkin
 */
@Path("/transfers")
@Produces("application/json")
public class TransfersResource {

  @Inject
  TransferService transferService;

  @POST
  @Consumes("application/json")
  public Response createTransfer(Transfer transfer) {
    transferService.transfer(transfer.getFrom(), transfer.getTo(), transfer.getAmount());
    return Response.noContent().build();
  }
}
