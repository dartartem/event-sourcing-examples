package net.chrisrichardson.eventstore.javaexamples.banking.commonauth;

import net.chrisrichardson.eventstore.javaexamples.banking.customers.view.commonapi.CustomerView;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

interface CustomerAuthRepository extends MongoRepository<CustomerView, String> {

  List<CustomerView> findByEmail(String email);

  List<CustomerView> findByEmailAndPassword(String email, String password);
}