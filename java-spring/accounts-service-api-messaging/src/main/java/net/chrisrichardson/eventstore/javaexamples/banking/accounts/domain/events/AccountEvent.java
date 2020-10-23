package net.chrisrichardson.eventstore.javaexamples.banking.accounts.domain.events;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="net.chrisrichardson.eventstore.javaexamples.banking.accountsservice.domain.Account")
public abstract class AccountEvent implements Event{
}
