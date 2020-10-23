package net.chrisrichardson.eventstore.javaexamples.banking.commonauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.TokenService;

import java.security.SecureRandom;

@Configuration
@ComponentScan
@EnableWebSecurity
@EnableMongoRepositories
@Import(AuthPropertiesConfiguration.class)
public class AuthConfiguration {

  @Bean
  public CustomerAuthService customerAuthService(CustomerAuthRepository customerAuthRepository) {
    return new CustomerAuthService(customerAuthRepository);
  }

  @Bean
  public TokenService tokenService(AuthProperties securityProperties) {
    KeyBasedPersistenceTokenService res = new KeyBasedPersistenceTokenService();
    res.setSecureRandom(new SecureRandom());
    res.setServerSecret(securityProperties.getServerSecret());
    res.setServerInteger(securityProperties.getServerInteger());

    return res;
  }
}
