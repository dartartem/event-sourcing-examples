package net.chrisrichardson.eventstore.javaexamples.banking.commonauth;

import net.chrisrichardson.eventstore.javaexamples.banking.customers.view.commonapi.CustomerView;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;

/**
 * Created by Main on 15.02.2016.
 */
public class CustomerAuthService {
  private CustomerAuthRepository customerAuthRepository;

  public CustomerAuthService(CustomerAuthRepository customerAuthRepository) {
    this.customerAuthRepository = customerAuthRepository;
  }

  public CustomerView findByEmail(String email) {
    CustomerView result = DataAccessUtils.uniqueResult(customerAuthRepository.findByEmail(email));
    if (result==null)
      throw new EmptyResultDataAccessException(1);

    return result;
  }

  public CustomerView findByEmailAndPassword(String email, String password) {
    CustomerView result =  DataAccessUtils.uniqueResult(customerAuthRepository.findByEmailAndPassword(email, password));
    if (result==null)
      throw new EmptyResultDataAccessException(1);

    return result;
  }
}
