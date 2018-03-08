package ru.ponkin.billing.services;

import java.math.BigDecimal;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import lombok.extern.slf4j.Slf4j;
import ru.ponkin.billing.dao.GenericDAO;
import ru.ponkin.billing.exceptions.InsufficientFundsException;
import ru.ponkin.billing.model.Account;

/**
 * @author Alexey Ponkin
 */
@Slf4j
@Singleton
public class TransferService {

  private final GenericDAO<Account> accountDAO;

  @Inject
  public TransferService(GenericDAO<Account> accountDAO) {
    this.accountDAO = accountDAO;
  }

  @Transactional
  public void transfer(Long fromId, Long toId, BigDecimal amount) {
    if (fromId.equals(toId)) {
      throw new IllegalArgumentException("Transfer can be made between two different accounts");
    }

    if (amount.signum() < 0) {
      throw new IllegalArgumentException("Transfer amount can`t be negative");
    }

    Optional<Account> fromAccountO;
    Optional<Account> toAccountO;

    if (fromId < toId) {
      fromAccountO = accountDAO.findByIdAndLock(fromId);
      toAccountO = accountDAO.findByIdAndLock(toId);
    } else {
      toAccountO = accountDAO.findByIdAndLock(toId);
      fromAccountO = accountDAO.findByIdAndLock(fromId);
    }

    if (!fromAccountO.filter(f -> canWithdraw(f, amount)).isPresent()) {
      throw new InsufficientFundsException(fromId);
    }

    fromAccountO.ifPresent(from -> toAccountO.ifPresent(to -> {
      from.setAmount(from.getAmount().subtract(amount));
      to.setAmount(to.getAmount().add(amount));
      accountDAO.update(from);
      accountDAO.update(to);
    }));
  }

  private boolean canWithdraw(Account account, BigDecimal amount) {
    return !(account.getAmount().subtract(amount).signum() < 0);
  }
}
