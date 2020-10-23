package net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice;

import io.eventuate.local.java.spring.javaclient.driver.EventuateDriverConfiguration;
import net.chrisrichardson.eventstore.javaexamples.banking.customersviewservice.web.CustomersViewWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CustomerViewConfiguration.class,
        CustomersViewWebConfiguration.class,
        EventuateDriverConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class CustomersViewServiceMain {

  public static void main(String[] args) {
    SpringApplication.run(CustomersViewServiceMain.class, args);
  }
}
