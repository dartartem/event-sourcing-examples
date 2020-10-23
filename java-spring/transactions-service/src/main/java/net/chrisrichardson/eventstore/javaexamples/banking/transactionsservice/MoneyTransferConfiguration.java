package net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.domain.MoneyTransfer;
import net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.domain.MoneyTransferCommand;
import net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.service.MoneyTransferService;
import net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.service.MoneyTransferWorkflow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEventHandlers
public class MoneyTransferConfiguration {

  @Bean
  public MoneyTransferService moneyTransferService(AggregateRepository<MoneyTransfer, MoneyTransferCommand> moneyTransferRepository) {
    return new MoneyTransferService(moneyTransferRepository);
  }

  @Bean
  public MoneyTransferWorkflow moneyTransferWorkflow() {
    return new MoneyTransferWorkflow();
  }

  @Bean
  public AggregateRepository<MoneyTransfer, MoneyTransferCommand> moneyTransferRepository(EventuateAggregateStore eventStore) {
    return new AggregateRepository<MoneyTransfer, MoneyTransferCommand>(MoneyTransfer.class, eventStore);
  }


}
