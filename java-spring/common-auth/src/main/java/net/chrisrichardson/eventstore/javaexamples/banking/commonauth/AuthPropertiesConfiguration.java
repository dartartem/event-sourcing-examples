package net.chrisrichardson.eventstore.javaexamples.banking.commonauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value="classpath:auth.properties")
public class AuthPropertiesConfiguration {
  @Bean
  public AuthProperties authProperties() {
    return new AuthProperties();
  }
}
