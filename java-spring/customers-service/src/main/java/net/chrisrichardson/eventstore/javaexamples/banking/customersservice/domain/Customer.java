package net.chrisrichardson.eventstore.javaexamples.banking.customersservice.domain;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import net.chrisrichardson.eventstore.javaexamples.banking.common.customers.CustomerInfo;
import net.chrisrichardson.eventstore.javaexamples.banking.customers.domain.events.CustomerAddedToAccount;
import net.chrisrichardson.eventstore.javaexamples.banking.customers.domain.events.CustomerCreatedEvent;
import net.chrisrichardson.eventstore.javaexamples.banking.customers.domain.events.CustomerToAccountDeleted;

import java.util.List;

public class Customer extends ReflectiveMutableCommandProcessingAggregate<Customer, CustomerCommand> {

  private CustomerInfo customerInfo;

  public List<Event> process(CreateCustomerCommand cmd) {
    return EventUtil.events(new CustomerCreatedEvent(cmd.getCustomerInfo()));
  }

  public List<Event> process(AddToAccountCommand cmd) {
    return EventUtil.events(new CustomerAddedToAccount(cmd.getToAccountInfo()));
  }
  public List<Event> process(DeleteToAccountCommand cmd) {
    return EventUtil.events(new CustomerToAccountDeleted(cmd.getAccountId()));
  }

  public void apply(CustomerCreatedEvent event) {
    customerInfo = event.getCustomerInfo();
  }

  public void apply(CustomerAddedToAccount event) {
  }
  public void apply(CustomerToAccountDeleted event) {
  }

  public CustomerInfo getCustomerInfo() {
    return customerInfo;
  }
}
