package ru.ponkin.billing.modules;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistService;

/**
 * @author Alexey Ponkin
 */
@Singleton
public class JpaInitializer {

  @Inject
  public JpaInitializer(final PersistService service) {
    service.start();
  }
}