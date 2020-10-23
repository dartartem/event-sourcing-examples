package net.chrisrichardson.eventstore.javaexamples.banking.accountsviewservice.service;

import net.chrisrichardson.eventstore.javaexamples.banking.accountsviewservice.domain.AccountInfo;
import net.chrisrichardson.eventstore.javaexamples.banking.accountsviewservice.domain.AccountInfoRepository;
import net.chrisrichardson.eventstore.javaexamples.banking.accountsviewservice.domain.AccountNotFoundException;

import java.util.List;

public class AccountQueryService {

  private AccountInfoRepository accountInfoRepository;

  public AccountQueryService(AccountInfoRepository accountInfoRepository) {
    this.accountInfoRepository = accountInfoRepository;
  }

  public AccountInfo findByAccountId(String accountId) {
    return accountInfoRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
  }

  public List<AccountInfo> findByCustomerId(String customerId) {
      return accountInfoRepository.findByCustomerId(customerId);
  }
}
