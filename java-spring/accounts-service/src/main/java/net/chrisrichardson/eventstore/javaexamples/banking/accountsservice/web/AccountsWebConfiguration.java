package net.chrisrichardson.eventstore.javaexamples.banking.accountsservice.web;

import net.chrisrichardson.eventstore.javaexamples.banking.commonswagger.CommonSwaggerConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CommonSwaggerConfiguration.class)
@ComponentScan
public class AccountsWebConfiguration {


}
