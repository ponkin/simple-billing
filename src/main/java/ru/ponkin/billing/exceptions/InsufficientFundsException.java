package ru.ponkin.billing.exceptions;

/**
 * @author Alexey Ponkin
 */
public class InsufficientFundsException extends RuntimeException {
  public InsufficientFundsException(Long fromId) {
    super(String.format("Insufficient funds for account %d", fromId));
  }
}
