package net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import net.chrisrichardson.eventstore.javaexamples.banking.transactions.domain.events.TransferDetails;
import net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.domain.CreateMoneyTransferCommand;
import net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.domain.MoneyTransfer;
import net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.domain.MoneyTransferCommand;

import java.util.concurrent.CompletableFuture;

public class MoneyTransferService {
  private final AggregateRepository<MoneyTransfer, MoneyTransferCommand> aggregateRepository;

  public MoneyTransferService(AggregateRepository<MoneyTransfer, MoneyTransferCommand> aggregateRepository) {
    this.aggregateRepository = aggregateRepository;
  }

  public CompletableFuture<EntityWithIdAndVersion<MoneyTransfer>> transferMoney(TransferDetails transferDetails) {
    return aggregateRepository.save(new CreateMoneyTransferCommand(transferDetails));
  }

}
