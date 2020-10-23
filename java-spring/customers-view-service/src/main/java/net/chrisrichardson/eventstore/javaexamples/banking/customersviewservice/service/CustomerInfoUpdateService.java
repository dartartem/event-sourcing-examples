package net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice.service;

import net.chrisrichardson.eventstore.javaexamples.banking.common.customers.CustomerInfo;
import net.chrisrichardson.eventstore.javaexamples.banking.common.customers.ToAccountInfo;
import net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice.domain.CustomerViewRepository;
import net.chrisrichardson.eventstore.javaexamples.banking.customers.view.commonapi.CustomerView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collections;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by Main on 04.02.2016.
 */
public class CustomerInfoUpdateService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  private CustomerViewRepository querySideCustomerRepository;
  private MongoTemplate mongoTemplate;

  public CustomerInfoUpdateService(CustomerViewRepository querySideCustomerRepository, MongoTemplate mongoTemplate) {
    this.querySideCustomerRepository = querySideCustomerRepository;
    this.mongoTemplate = mongoTemplate;
  }

  public void create(String id, CustomerInfo customerInfo) {
    try {
      querySideCustomerRepository.save(new CustomerView(id,
                      customerInfo.getName(),
                      customerInfo.getUserCredentials().getEmail(),
                      customerInfo.getUserCredentials().getPassword(),
                      customerInfo.getSsn(),
                      customerInfo.getPhoneNumber(),
                      customerInfo.getAddress(),
                      Collections.<String, ToAccountInfo>emptyMap()
              )
      );
      logger.info("Saved in mongo");
    } catch (DuplicateKeyException t) {
      logger.warn("When saving ", t);
    }
  }

  public void addToAccount(String id, ToAccountInfo accountInfo) {
    mongoTemplate.upsert(new Query(where("id").is(id)),
            new Update().
                    set("toAccounts." + accountInfo.getId(), accountInfo),
            CustomerView.class);
  }

  public void deleteToAccountFromAllCustomers(String accountId) {
    mongoTemplate.updateMulti(new Query(where("toAccounts." + accountId).exists(true)),
            new Update().
                    unset("toAccounts." + accountId),
            CustomerView.class);
  }

  public void deleteToAccount(String customerId, String accountId) {
    mongoTemplate.upsert(new Query(where("id").is(customerId)),
            new Update().
                    unset("toAccounts." + accountId),
            CustomerView.class);
  }
}
