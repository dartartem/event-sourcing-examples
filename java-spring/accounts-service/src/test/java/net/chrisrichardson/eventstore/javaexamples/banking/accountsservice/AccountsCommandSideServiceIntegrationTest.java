package net.chrisrichardson.eventstore.javaexamples.banking.accountsservice;

import net.chrisrichardson.eventstore.javaexamples.banking.accounts.webapi.CreateAccountRequest;
import net.chrisrichardson.eventstore.javaexamples.banking.accounts.webapi.CreateAccountResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AccountsCommandSideServiceTestConfiguration.class)
public class AccountsCommandSideServiceIntegrationTest {

  @LocalServerPort
  private int port;

  private String baseUrl(String path) {
    return "http://localhost:" + port + "/api" + path;
  }

  @Autowired
  RestTemplate restTemplate;


  @Test
  public void shouldCreateAccounts() {
    BigDecimal initialFromAccountBalance = new BigDecimal(500);
    BigDecimal initialToAccountBalance = new BigDecimal(100);
    String customerId = "00000000-00000000";
    String title = "My Account";

    final CreateAccountResponse fromAccount = restTemplate.postForEntity(baseUrl("/accounts"), new CreateAccountRequest(customerId, title, "", initialFromAccountBalance), CreateAccountResponse.class).getBody();
    final String fromAccountId = fromAccount.getAccountId();

    CreateAccountResponse toAccount = restTemplate.postForEntity(baseUrl("/accounts"), new CreateAccountRequest(customerId, title, "", initialToAccountBalance), CreateAccountResponse.class).getBody();
    String toAccountId = toAccount.getAccountId();

    Assert.assertNotNull(fromAccountId);
    Assert.assertNotNull(toAccountId);
  }
}
