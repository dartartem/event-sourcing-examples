package net.chrisrichardson.eventstore.javaexamples.banking.apigateway;

import net.chrisrichardson.eventstore.javaexamples.banking.commonauth.AuthConfigurationAdapter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AuthConfigurationAdapter.class)
@EnableAutoConfiguration
public class AuthControllerIntegrationTestConfiguration {
}
