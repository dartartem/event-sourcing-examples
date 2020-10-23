package net.chrisrichardson.eventstore.javaexamples.banking.transactions.domain.events;

public class CreditRecordedEvent extends MoneyTransferEvent {
  private TransferDetails details;

  private CreditRecordedEvent() {
  }

  public CreditRecordedEvent(TransferDetails details) {
    this.details = details;
  }

  public TransferDetails getDetails() {
    return details;
  }
}
