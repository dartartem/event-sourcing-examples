package net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice.service;

import io.eventuate.common.id.IdGenerator;
import io.eventuate.common.id.IdGeneratorImpl;
import io.eventuate.javaclient.spring.jdbc.EmbeddedTestAggregateStoreConfiguration;
import net.chrisrichardson.eventstore.javaexamples.banking.common.customers.CustomerInfo;
import net.chrisrichardson.eventstore.javaexamples.banking.common.customers.ToAccountInfo;
import net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice.CustomerViewConfiguration;
import net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice.web.CustomersViewWebConfiguration;
import net.chrisrichardson.eventstore.javaexamples.banking.customers.view.commonapi.CustomerView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;

import static net.chrisrichardson.eventstorestore.javaexamples.testutil.CustomersTestUtils.generateCustomerInfo;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CustomerInfoUpdateServiceTest.CustomerInfoUpdateServiceTestConfiguration.class)
public class CustomerInfoUpdateServiceTest {

  @Configuration
  @EnableAutoConfiguration
  @Import({CustomerViewConfiguration.class,
          CustomersViewWebConfiguration.class,
          EmbeddedTestAggregateStoreConfiguration.class})
  public static class CustomerInfoUpdateServiceTestConfiguration {

  }

  @Autowired
  private CustomerInfoUpdateService customerInfoUpdateService;

  @Autowired
  private CustomerQueryService customerQueryService;

  @Test
  public void shouldSaveQuerysideCustomer() throws ExecutionException, InterruptedException {
    IdGenerator x = new IdGeneratorImpl();
    String customerId = x.genId().asString();

    CustomerInfo customerInfo = generateCustomerInfo();
    customerInfoUpdateService.create(customerId, customerInfo);

    CustomerView customerView = customerQueryService.findByCustomerId(customerId).get();

    assertEquals(customerInfo.getName(), customerView.getName());
    assertEquals(customerInfo.getAddress(), customerView.getAddress());
    assertEquals(customerInfo.getUserCredentials().getEmail(), customerView.getEmail());
    assertEquals(customerInfo.getPhoneNumber(), customerView.getPhoneNumber());
    assertEquals(customerInfo.getSsn(), customerView.getSsn());
  }

  @Test
  public void shouldAddAndDeleteToAccount() throws ExecutionException, InterruptedException {
    IdGenerator x = new IdGeneratorImpl();
    String customerId = x.genId().asString();
    String accountId = x.genId().asString();

    CustomerInfo customerInfo = generateCustomerInfo();
    customerInfoUpdateService.create(customerId, customerInfo);

    ToAccountInfo toAccountInfo = new ToAccountInfo(accountId, "title", "owner", "description");

    customerInfoUpdateService.addToAccount(customerId, toAccountInfo);

    CustomerView customerView = customerQueryService.findByCustomerId(customerId).get();

    assertTrue(customerView.getToAccounts().containsKey(accountId));
    assertEquals(toAccountInfo, customerView.getToAccounts().get(accountId));

    customerInfoUpdateService.deleteToAccountFromAllCustomers(accountId);
    customerView = customerQueryService.findByCustomerId(customerId).get();

    assertFalse(customerView.getToAccounts().containsKey(accountId));
  }
}