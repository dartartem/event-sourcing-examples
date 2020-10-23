package net.chrisrichardson.eventstore.javaexamples.banking.accounts.domain.events;

import java.math.BigDecimal;

public class AccountCreditedEvent extends AccountChangedEvent {

  private AccountCreditedEvent() {
  }

  public AccountCreditedEvent(BigDecimal amount, String transactionId) {
    super(amount, transactionId);
  }

}
