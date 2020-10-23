package net.chrisrichardson.eventstore.javaexamples.banking.transactions.domain.events;


public class MoneyTransferCreatedEvent extends MoneyTransferEvent {
  private TransferDetails details;

  private MoneyTransferCreatedEvent() {
  }

  public MoneyTransferCreatedEvent(TransferDetails details) {
    this.details = details;
  }

  public TransferDetails getDetails() {
    return details;
  }
}

