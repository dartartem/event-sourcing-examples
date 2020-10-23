package net.chrisrichardson.eventstore.javaexamples.banking.transactions.domain.events;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.domain.MoneyTransfer")
public abstract class MoneyTransferEvent implements Event {
}
