package net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice.service;

import io.eventuate.CompletableFutureUtil;
import net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice.domain.CustomerViewRepository;
import net.chrisrichardson.eventstore.javaexamples.banking.customers.view.commonapi.CustomerView;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CustomerQueryService {

  private CustomerViewRepository querySideCustomerRepository;

  public CustomerQueryService(CustomerViewRepository querySideCustomerRepository) {
    this.querySideCustomerRepository = querySideCustomerRepository;
  }

  public CompletableFuture<CustomerView> findByCustomerId(String customerId) {
    return querySideCustomerRepository.findById(customerId).map(c -> CompletableFuture.completedFuture(c)).orElse(CompletableFutureUtil.failedFuture(new EmptyResultDataAccessException(1)));
  }

  public CompletableFuture<List<CustomerView>> findByEmail(String email) {
    List<CustomerView> customers = querySideCustomerRepository.findByEmailLike(email);
    if (customers.isEmpty())
      return CompletableFutureUtil.failedFuture(new EmptyResultDataAccessException(1));
    else
      return CompletableFuture.completedFuture(customers);
  }
}
