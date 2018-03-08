package ru.ponkin.billing.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import ru.ponkin.billing.exceptions.InsufficientFundsException;
import ru.ponkin.billing.resources.AccountsResource;
import ru.ponkin.billing.resources.TransfersResource;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Alexey Ponkin
 */
public class ResourcesModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(AccountsResource.class);
    bind(TransfersResource.class);
    bind(InsufficientFundsExceptionMapper.class);
  }

  @Provides
  @Singleton
  ObjectMapper objectMapper() {
    final ObjectMapper mapper = new ObjectMapper();
    Jdk8Module module = new Jdk8Module();
    module.configureAbsentsAsNulls(true);
    mapper.registerModule(module);
    return mapper;
  }

}

@Provider
@Singleton
class InsufficientFundsExceptionMapper implements ExceptionMapper<InsufficientFundsException> {
  @Override
  public Response toResponse(final InsufficientFundsException exception) {
    return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
  }
}

@Provider
@Singleton
class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

  @Override
  public Response toResponse(IllegalArgumentException exception) {
    return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
  }
}