package ru.ponkin.billing.modules;


import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.persist.jpa.JpaPersistModule;

import ru.ponkin.billing.dao.GenericDAO;
import ru.ponkin.billing.dao.JpaAccountDAO;
import ru.ponkin.billing.model.Account;


/**
 * @author Alexey Ponkin
 */
public class PersistenceModule extends AbstractModule {

  private final String persistenceUnit;

  public PersistenceModule(String persistenceUnit) {
    this.persistenceUnit = persistenceUnit;
  }

  @Override
  protected void configure() {
    install(new JpaPersistModule(persistenceUnit));
    bind(JpaInitializer.class).asEagerSingleton();
    bind(new TypeLiteral<GenericDAO<Account>>() {}).to(new TypeLiteral<JpaAccountDAO>() {});
  }

}
