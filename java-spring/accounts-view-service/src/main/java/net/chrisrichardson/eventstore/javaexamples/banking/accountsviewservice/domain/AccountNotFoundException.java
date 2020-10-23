package net.chrisrichardson.eventstore.javaexamples.banking.accountsviewservice.domain;

public class AccountNotFoundException extends RuntimeException {

  public AccountNotFoundException(String accountId) {
    super("Account not found " + accountId);
  }
}
