package net.chrisrichardson.eventstore.javaexamples.banking.accountsviewservice;

import io.eventuate.local.java.spring.javaclient.driver.EventuateDriverConfiguration;
import net.chrisrichardson.eventstore.javaexamples.banking.accountsviewservice.web.AccountViewWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AccountViewConfiguration.class, AccountViewWebConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class AccountsViewServiceMain {

  public static void main(String[] args) {
    SpringApplication.run(AccountsViewServiceMain.class, args);
  }
}
