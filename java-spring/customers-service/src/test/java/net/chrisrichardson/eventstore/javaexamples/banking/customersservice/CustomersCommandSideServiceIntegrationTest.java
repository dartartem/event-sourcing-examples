package net.chrisrichardson.eventstore.javaexamples.banking.customersservice;

import net.chrisrichardson.eventstore.javaexamples.banking.common.customers.CustomerInfo;
import net.chrisrichardson.eventstore.javaexamples.banking.customers.webapi.CustomerResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static net.chrisrichardson.eventstorestore.javaexamples.testutil.CustomersTestUtils.generateCustomerInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CustomersCommandSideServiceTestConfiguration.class)
public class CustomersCommandSideServiceIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl(String path) {
        return "http://localhost:" + port + "/api" + path;
    }

    @Autowired
    RestTemplate restTemplate;


    @Test
    public void shouldCreateCustomer() {
        CustomerInfo customerInfo = generateCustomerInfo();

        final CustomerResponse customerResponse = restTemplate.postForEntity(baseUrl("/customers"), customerInfo, CustomerResponse.class).getBody();
        final String customerId = customerResponse.getId();

        Assert.assertNotNull(customerId);
        Assert.assertEquals(customerInfo, customerResponse.getCustomerInfo());
    }

}
