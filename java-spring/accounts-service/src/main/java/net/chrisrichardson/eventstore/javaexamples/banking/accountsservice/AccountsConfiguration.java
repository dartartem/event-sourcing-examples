package net.chrisrichardson.eventstore.javaexamples.banking.accountsservice;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import net.chrisrichardson.eventstore.javaexamples.banking.accountsservice.domain.Account;
import net.chrisrichardson.eventstore.javaexamples.banking.accountsservice.domain.AccountCommand;
import net.chrisrichardson.eventstore.javaexamples.banking.accountsservice.service.AccountService;
import net.chrisrichardson.eventstore.javaexamples.banking.accountsservice.service.AccountWorkflow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEventHandlers
public class AccountsConfiguration {

  @Bean
  public AccountWorkflow accountWorkflow() {
    return new AccountWorkflow();
  }


  @Bean
  public AccountService accountService(AggregateRepository<Account, AccountCommand> accountRepository) {
    return new AccountService(accountRepository);
  }

  @Bean
  public AggregateRepository<Account, AccountCommand> accountRepository(EventuateAggregateStore eventStore) {
    return new AggregateRepository<Account, AccountCommand>(Account.class, eventStore);
  }
}


