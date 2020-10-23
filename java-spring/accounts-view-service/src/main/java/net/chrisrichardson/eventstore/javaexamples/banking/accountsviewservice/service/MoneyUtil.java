package net.chrisrichardson.eventstore.javaexamples.banking.accountsviewservice.service;

import java.math.BigDecimal;

public class MoneyUtil {

  public static long toIntegerRepr(BigDecimal d) {
    return d.multiply(new BigDecimal(100)).longValueExact();
  }

}
