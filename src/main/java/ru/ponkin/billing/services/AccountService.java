package ru.ponkin.billing.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import ru.ponkin.billing.dao.GenericDAO;
import ru.ponkin.billing.model.Account;

import java.util.Optional;

/**
 * @author Alexey Ponkin
 */
@Singleton
public class AccountService {

  private final GenericDAO<Account> accountDAO;

  @Inject
  public AccountService(GenericDAO<Account> accountDAO) {
    this.accountDAO = accountDAO;
  }

  @Transactional
  public Account addAccount(Account account) {
    return accountDAO.create(account);
  }

  public Optional<Account> findById(Long id) {
    return accountDAO.findById(id);
  }

}
