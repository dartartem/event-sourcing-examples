package net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice.domain;

import net.chrisrichardson.eventstore.javaexamples.banking.customers.view.commonapi.CustomerView;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerViewRepository extends MongoRepository<CustomerView, String> {

  List<CustomerView> findByEmailLike(String email);
}
