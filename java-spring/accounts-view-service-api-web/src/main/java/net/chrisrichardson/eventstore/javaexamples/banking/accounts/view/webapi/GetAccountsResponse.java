package net.chrisrichardson.eventstore.javaexamples.banking.accounts.view.webapi;

import java.util.List;

public class GetAccountsResponse {
  private List<GetAccountResponse> accounts;

  public GetAccountsResponse() {
  }

  public GetAccountsResponse(List<GetAccountResponse> accounts) {
    this.accounts = accounts;
  }

  public List<GetAccountResponse> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<GetAccountResponse> accounts) {
    this.accounts = accounts;
  }
}
