package net.chrisrichardson.eventstore.javaexamples.banking.customers.domain.events;


import io.eventuate.Event;
import io.eventuate.EventEntity;

/**
 * Created by Main on 11.02.2016.
 */
@EventEntity(entity = "net.chrisrichardson.eventstore.javaexamples.banking.customersservice.domain.Customer")
public abstract class CustomerEvent implements Event {
}