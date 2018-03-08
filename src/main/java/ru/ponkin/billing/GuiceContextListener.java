package ru.ponkin.billing;

import com.google.inject.Module;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import ru.ponkin.billing.modules.PersistenceModule;
import ru.ponkin.billing.modules.ResourcesModule;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Ponkin
 */
public class GuiceContextListener extends GuiceResteasyBootstrapServletContextListener {

  private final String persistenceUnit;

  public GuiceContextListener(String persistenceUnit) {
    this.persistenceUnit = persistenceUnit;
  }

  @Override
  protected List<? extends Module> getModules(final ServletContext context){
    final List<Module> result = new ArrayList<>();
    result.add(new PersistenceModule(persistenceUnit));
    result.add(new ResourcesModule());
    return result;
  }
}