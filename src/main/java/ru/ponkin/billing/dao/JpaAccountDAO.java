package ru.ponkin.billing.dao;

import ru.ponkin.billing.model.Account;

/**
 * @author Alexey Ponkin
 */
public class JpaAccountDAO extends JpaGenericDAO<Account> {

  public JpaAccountDAO() {
    super(Account.class);
  }

}
