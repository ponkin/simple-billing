package ru.ponkin.billing.resources;

import com.google.inject.Inject;
import ru.ponkin.billing.model.Account;
import ru.ponkin.billing.services.AccountService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * @author Alexey Ponkin
 */
@Path("/accounts")
@Produces("application/json")
public class AccountsResource {

  @Inject
  AccountService accountService;

  @POST
  public Response createAccount() {
    Account account = new Account();
    accountService.addAccount(account);
    return Response.ok().entity(account).build();
  }

  @GET
  @Path("/{accountId}")
  public Response getAccount(@PathParam("accountId") Long accountId) {
    Optional<Account> account = accountService.findById(accountId);
    if(account.isPresent()) {
      return Response.ok().entity(account.get()).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

}