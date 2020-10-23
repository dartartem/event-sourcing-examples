package net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.service;


import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import net.chrisrichardson.eventstore.javaexamples.banking.accounts.domain.events.AccountCreditedEvent;
import net.chrisrichardson.eventstore.javaexamples.banking.accounts.domain.events.AccountDebitFailedDueToInsufficientFundsEvent;
import net.chrisrichardson.eventstore.javaexamples.banking.accounts.domain.events.AccountDebitedEvent;
import net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.domain.MoneyTransfer;
import net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.domain.RecordCreditCommand;
import net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.domain.RecordDebitCommand;
import net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.domain.RecordDebitFailedCommand;

import java.util.concurrent.CompletableFuture;

@EventSubscriber(id="transferEventHandlers")
public class MoneyTransferWorkflow {

  @EventHandlerMethod
  public CompletableFuture<EntityWithIdAndVersion<MoneyTransfer>> recordDebit(EventHandlerContext<AccountDebitedEvent> ctx) {
    return ctx.update(MoneyTransfer.class, ctx.getEvent().getTransactionId(), new RecordDebitCommand());
  }

  @EventHandlerMethod
  public CompletableFuture<EntityWithIdAndVersion<MoneyTransfer>> recordDebitFailed(EventHandlerContext<AccountDebitFailedDueToInsufficientFundsEvent> ctx) {
    return ctx.update(MoneyTransfer.class, ctx.getEvent().getTransactionId(), new RecordDebitFailedCommand());
  }

  @EventHandlerMethod
  public CompletableFuture<EntityWithIdAndVersion<MoneyTransfer>> recordCredit(EventHandlerContext<AccountCreditedEvent> ctx) {
    return ctx.update(MoneyTransfer.class, ctx.getEvent().getTransactionId(), new RecordCreditCommand());
  }


}
