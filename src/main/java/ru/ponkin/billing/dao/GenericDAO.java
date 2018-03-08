package ru.ponkin.billing.dao;

import java.util.Optional;

/**
 * @author Alexey Ponkin
 */
public interface GenericDAO<E> {

  E create(E entity);

  E update(E entity);

  Optional<E> findById(Long id);

  Optional<E> findByIdAndLock(Long id);

}
