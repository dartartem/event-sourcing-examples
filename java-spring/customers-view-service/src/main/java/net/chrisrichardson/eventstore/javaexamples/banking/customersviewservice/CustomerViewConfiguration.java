package net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice;

import io.eventuate.javaclient.spring.EnableEventHandlers;
import net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice.service.CustomerInfoUpdateService;
import net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice.service.CustomerQueryService;
import net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice.service.CustomerQueryWorkflow;
import net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice.domain.CustomerViewRepository;
import net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice.domain.ViewDependencyChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by Main on 04.02.2016.
 */
@Configuration
@EnableMongoRepositories
@EnableEventHandlers
public class CustomerViewConfiguration {
  @Bean
  public CustomerQueryWorkflow customerQueryWorkflow(CustomerInfoUpdateService accountInfoUpdateService) {
    return new CustomerQueryWorkflow(accountInfoUpdateService);
  }

  @Bean
  public CustomerInfoUpdateService customerInfoUpdateService(CustomerViewRepository querySideCustomerRepository, MongoTemplate mongoTemplate) {
    return new CustomerInfoUpdateService(querySideCustomerRepository, mongoTemplate);
  }

  @Bean
  public CustomerQueryService customerQueryService(CustomerViewRepository accountInfoRepository) {
    return new CustomerQueryService(accountInfoRepository);
  }


  @Bean
  public ViewDependencyChecker querysideDependencyChecker(MongoTemplate mongoTemplate) {
    return new ViewDependencyChecker(mongoTemplate);
  }

}
