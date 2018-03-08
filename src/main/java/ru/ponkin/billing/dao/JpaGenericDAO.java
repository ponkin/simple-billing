package ru.ponkin.billing.dao;

import com.google.inject.Inject;
import com.google.inject.Provider;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.Optional;

/**
 * @author Alexey Ponkin
 */
public abstract class JpaGenericDAO<E> implements GenericDAO<E> {

  @Inject
  private Provider<EntityManager> entityManagerProvider;

  private final Class<E> clazz;

  public JpaGenericDAO(Class<E> clazz) {
    this.clazz = clazz;
  }

  @Override
  public E create(E entity) {
    getEntityManager().persist(entity);
    return entity;
  }

  @Override
  public E update(E entity) {
    return entity;
  }

  @Override
  public Optional<E> findById(Long id) {
    return Optional.ofNullable(getEntityManager().find(clazz, id));
  }

  @Override
  public Optional<E> findByIdAndLock(Long id) {
    return Optional.ofNullable(getEntityManager().find(clazz, id, LockModeType.PESSIMISTIC_WRITE));
  }

  protected EntityManager getEntityManager() {
    return entityManagerProvider.get();
  }

}
