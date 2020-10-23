package net.chrisrichardson.eventstore.javaexamples.banking.commonauth;

import net.chrisrichardson.eventstore.javaexamples.banking.customers.view.commonapi.CustomerView;
import net.chrisrichardson.eventstore.javaexamples.banking.commonauth.filter.StatelessAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@Import(AuthConfiguration.class)
public class AuthConfigurationAdapter extends WebSecurityConfigurerAdapter {

  @Autowired
  private TokenAuthenticationService tokenAuthenticationService;

  @Autowired
  private CustomerAuthService customerAuthService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsServiceBean());
  }

  @Override
  public UserDetailsService userDetailsServiceBean() {
    return email -> {
      CustomerView customer = customerAuthService.findByEmail(email);
      return new User(email, "{noop}" + customer.getPassword(), true, true, true, true,
              AuthorityUtils.createAuthorityList("USER"));
    };
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf()
              .disable()
            .httpBasic()
              .and()
            .authorizeRequests()
              .antMatchers(HttpMethod.POST, "/api/customers", "/api/login").permitAll()
              .antMatchers("/api/**").permitAll()
              .anyRequest().permitAll()
              .and()
            .addFilterAfter(new StatelessAuthenticationFilter(tokenAuthenticationService), BasicAuthenticationFilter.class);
  }
}
