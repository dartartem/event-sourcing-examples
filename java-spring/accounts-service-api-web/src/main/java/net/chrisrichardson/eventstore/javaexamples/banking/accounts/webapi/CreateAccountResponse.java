package net.chrisrichardson.eventstore.javaexamples.banking.accounts.webapi;


public class CreateAccountResponse {
  
  private String accountId;

  public CreateAccountResponse() {
  }

  public CreateAccountResponse(String accountId) {
    this.accountId = accountId;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }
}
