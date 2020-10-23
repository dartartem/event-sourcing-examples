package net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.domain;


import net.chrisrichardson.eventstore.javaexamples.banking.transactions.domain.events.TransferDetails;

public class CreateMoneyTransferCommand implements MoneyTransferCommand {
  private TransferDetails details;

  public TransferDetails getDetails() {
    return details;
  }

  public CreateMoneyTransferCommand(TransferDetails details) {

    this.details = details;
  }
}
