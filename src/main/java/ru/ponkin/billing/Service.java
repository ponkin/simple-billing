package ru.ponkin.billing;

/**
 * @author Alexey Ponkin
 */
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Service {

  private static final String CONTEXT_ROOT = "/";

  public static void main(String[] args) throws Exception {
    Service example = new Service();
    example.start();
  }

  public void start() throws Exception {

    Server server = new Server(8000);
    ServletContextHandler context =
            new ServletContextHandler(server, CONTEXT_ROOT, ServletContextHandler.SESSIONS);

    context.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));

    GuiceContextListener contextListener = new GuiceContextListener("billing");
    context.addEventListener(contextListener);
    context.addServlet(HttpServletDispatcher.class, "/*");

    server.start();
    server.join();
  }


}
