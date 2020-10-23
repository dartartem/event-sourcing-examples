package net.chrisrichardson.eventstore.javaexamples.banking.customersservice.service;


import net.chrisrichardson.eventstore.javaexamples.banking.customersservice.domain.Customer;
import net.chrisrichardson.eventstorestore.javaexamples.testutil.AbstractEntityEventTest;

public class CustomerEventTest extends AbstractEntityEventTest {

  @Override
  protected Class<Customer> entityClass() {
    return Customer.class;
  }
}