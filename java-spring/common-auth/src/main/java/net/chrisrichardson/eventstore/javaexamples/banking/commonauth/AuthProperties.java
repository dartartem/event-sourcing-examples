package net.chrisrichardson.eventstore.javaexamples.banking.commonauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

public class AuthProperties {
  @Value("${auth.server.secret}")
  private String serverSecret;

  @Value("${auth.server.integer}")
  private Integer serverInteger;

  public String getServerSecret() {
    return serverSecret;
  }

  public void setServerSecret(String serverSecret) {
    this.serverSecret = serverSecret;
  }

  public Integer getServerInteger() {
    return serverInteger;
  }

  public void setServerInteger(Integer serverInteger) {
    this.serverInteger = serverInteger;
  }
}
