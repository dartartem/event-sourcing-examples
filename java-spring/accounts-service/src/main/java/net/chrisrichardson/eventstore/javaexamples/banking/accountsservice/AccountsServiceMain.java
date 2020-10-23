package net.chrisrichardson.eventstore.javaexamples.banking.accountsservice;

import io.eventuate.local.java.spring.javaclient.driver.EventuateDriverConfiguration;
import net.chrisrichardson.eventstore.javaexamples.banking.accountsservice.web.AccountsWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AccountsConfiguration.class, AccountsWebConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class AccountsServiceMain {

  public static void main(String[] args) {
    SpringApplication.run(AccountsServiceMain.class, args);
  }
}
