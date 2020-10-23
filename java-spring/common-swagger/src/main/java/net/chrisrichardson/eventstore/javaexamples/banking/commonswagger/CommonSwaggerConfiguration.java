package net.chrisrichardson.eventstore.javaexamples.banking.commonswagger;

import io.eventuate.util.spring.swagger.EventuateSwaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class CommonSwaggerConfiguration {

  @Bean
  public EventuateSwaggerConfig eventuateSwaggerConfig() {
    return () -> "net.chrisrichardson.eventstore.javaexamples.banking";
  }
}
