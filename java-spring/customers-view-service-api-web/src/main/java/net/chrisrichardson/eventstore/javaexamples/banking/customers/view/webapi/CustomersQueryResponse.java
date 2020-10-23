package net.chrisrichardson.eventstore.javaexamples.banking.customers.view.webapi;

import net.chrisrichardson.eventstore.javaexamples.banking.customers.view.commonapi.CustomerView;

import java.util.List;

/**
 * Created by Main on 05.02.2016.
 */
public class CustomersQueryResponse {

  private List<CustomerView> customers;

  public CustomersQueryResponse() {
  }

  public CustomersQueryResponse(List<CustomerView> customers) {
    this.customers = customers;
  }

  public List<CustomerView> getCustomers() {
    return customers;
  }

  public void setCustomers(List<CustomerView> customers) {
    this.customers = customers;
  }
}
