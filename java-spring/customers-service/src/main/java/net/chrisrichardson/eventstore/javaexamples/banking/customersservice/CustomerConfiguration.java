package net.chrisrichardson.eventstore.javaexamples.banking.customersservice;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import net.chrisrichardson.eventstore.javaexamples.banking.customersservice.service.CustomerService;
import net.chrisrichardson.eventstore.javaexamples.banking.customersservice.domain.Customer;
import net.chrisrichardson.eventstore.javaexamples.banking.customersservice.domain.CustomerCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEventHandlers
public class CustomerConfiguration {

  @Bean
  public CustomerService customerService(AggregateRepository<Customer, CustomerCommand> customerRepository) {
    return new CustomerService(customerRepository);
  }

  @Bean
  public AggregateRepository<Customer, CustomerCommand> customerRepository(EventuateAggregateStore eventStore) {
    return new AggregateRepository<Customer, CustomerCommand>(Customer.class, eventStore);
  }

}


