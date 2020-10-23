package net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.service;

import net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.domain.MoneyTransfer;
import net.chrisrichardson.eventstorestore.javaexamples.testutil.AbstractEntityEventTest;

public class MoneyTransferEventTest extends AbstractEntityEventTest {

  @Override
  protected Class<?> entityClass() {
    return MoneyTransfer.class;
  }

}
