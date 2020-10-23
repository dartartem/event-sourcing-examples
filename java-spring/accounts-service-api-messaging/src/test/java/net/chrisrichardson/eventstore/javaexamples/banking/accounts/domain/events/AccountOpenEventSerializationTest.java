package net.chrisrichardson.eventstore.javaexamples.banking.accounts.domain.events;

import io.eventuate.common.json.mapper.JSonMapper;
import net.chrisrichardson.eventstore.javaexamples.banking.accounts.domain.events.AccountOpenedEvent;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountOpenEventSerializationTest {

  @Test
  public void shouldSerde() {

    AccountOpenedEvent event = new AccountOpenedEvent("00000000-00000000", "My Account", new BigDecimal(55), "");
    String json = JSonMapper.toJson(event);

    AccountOpenedEvent event2 = JSonMapper.fromJson(json, AccountOpenedEvent.class);

    Assert.assertEquals(event.getInitialBalance(), event2.getInitialBalance());
  }
}
