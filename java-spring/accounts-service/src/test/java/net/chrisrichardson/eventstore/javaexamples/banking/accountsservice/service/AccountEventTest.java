package net.chrisrichardson.eventstore.javaexamples.banking.accountsservice.service;


import net.chrisrichardson.eventstore.javaexamples.banking.accountsservice.domain.Account;
import net.chrisrichardson.eventstorestore.javaexamples.testutil.AbstractEntityEventTest;

public class AccountEventTest extends AbstractEntityEventTest {

  @Override
  protected Class<Account> entityClass() {
    return Account.class;
  }
}