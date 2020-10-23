package net.chrisrichardson.eventstore.javaexamples.banking.accounts.domain.events;

import java.math.BigDecimal;

public class AccountDebitedEvent extends AccountChangedEvent {

  private AccountDebitedEvent() {
  }

  public AccountDebitedEvent(BigDecimal amount, String transactionId) {
    super(amount, transactionId);
  }

}
