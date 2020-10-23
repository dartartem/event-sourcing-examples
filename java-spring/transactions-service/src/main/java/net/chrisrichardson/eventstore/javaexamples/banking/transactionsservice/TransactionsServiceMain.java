package net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice;

import io.eventuate.local.java.spring.javaclient.driver.EventuateDriverConfiguration;
import net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.web.MoneyTransferWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MoneyTransferConfiguration.class,
        MoneyTransferWebConfiguration.class,
        EventuateDriverConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class TransactionsServiceMain {

  public static void main(String[] args) {
    SpringApplication.run(TransactionsServiceMain.class, args);
  }
}
