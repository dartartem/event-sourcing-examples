package net.chrisrichardson.eventstore.javaexamples.banking.accountsviewservice.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountInfoRepository extends MongoRepository<AccountInfo, String> {

    List<AccountInfo> findByCustomerId(String customerId);
}
