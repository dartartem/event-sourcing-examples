package net.chrisrichardson.eventstorestore.javaexamples.testutil;

import net.chrisrichardson.eventstore.javaexamples.banking.common.customers.*;
import net.chrisrichardson.eventstore.javaexamples.banking.customers.view.commonapi.CustomerView;
import org.junit.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

import static net.chrisrichardson.eventstorestore.javaexamples.testutil.TestUtil.eventually;

public class CustomersTestUtils {

  private RestTemplate restTemplate;
  private String customersBaseUrl;

  public CustomersTestUtils(RestTemplate restTemplate, String customersBaseUrl) {
    this.restTemplate = restTemplate;
    this.customersBaseUrl = customersBaseUrl;
  }

  public void assertCustomerResponse(final String customerId, final CustomerInfo customerInfo) {
    AuthenticatedRestTemplate art = new AuthenticatedRestTemplate(restTemplate, customerInfo.getUserCredentials());
    eventually(
            () -> CompletableFuture.completedFuture(art.getForEntity(customersBaseUrl + customerId, CustomerView.class)),
            querySideCustomer -> {
              Assert.assertEquals(customerId, querySideCustomer.getId());
              assertQuerySideCustomerEqualscCustomerInfo(querySideCustomer, customerInfo);
            });
  }

  public void assertQuerySideCustomerEqualscCustomerInfo(CustomerView customerView, CustomerInfo customerInfo) {
    Assert.assertEquals(customerView.getName(), customerInfo.getName());
    Assert.assertEquals(customerView.getEmail(), customerInfo.getUserCredentials().getEmail());
    Assert.assertEquals(customerView.getPhoneNumber(), customerInfo.getPhoneNumber());
    Assert.assertEquals(customerView.getSsn(), customerInfo.getSsn());
    Assert.assertEquals(customerView.getAddress(), customerInfo.getAddress());
  }

  public static CustomerInfo generateCustomerInfo() {
    return generateCustomerInfo(uniqueEmail());
  }

  public static CustomerInfo generateCustomerInfo(String email) {
    return new CustomerInfo(
            new Name("John", "Doe"),
            new UserCredentials(email, "simple_password"),
            "000-00-0000",
            "1-111-111-1111",
            new Address("street 1",
                    "street 2",
                    "City",
                    "State",
                    "1111111")
    );
  }

  public static ToAccountInfo generateToAccountInfo() {
    return new ToAccountInfo("11111111-11111111", "New Account", "John Doe", "");
  }

  private static String uniqueEmail() {
    return System.currentTimeMillis() + "@email.com";
  }
}
