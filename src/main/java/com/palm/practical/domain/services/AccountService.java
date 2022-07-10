package com.palm.practical.domain.services;

import com.palm.practical.domain.models.Account;
import com.palm.practical.domain.records.AccountQueryResult;
import com.palm.practical.ports.in.AccountCreateUseCase;
import com.palm.practical.ports.in.AccountMoneyDepositUseCase;
import com.palm.practical.ports.in.AccountMoneyTransferUseCase;
import com.palm.practical.ports.in.AccountQueryUseCase;
import com.palm.practical.ports.out.AccountOutputPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountService implements AccountCreateUseCase,
                                       AccountMoneyDepositUseCase,
                                       AccountMoneyTransferUseCase,
                                       AccountQueryUseCase {

  private final AccountOutputPort accountOutputPort;

  @Override
  public Account createAccount(Account account) {
    final Account createdAccount = accountOutputPort.saveAccount(account);
    return createdAccount;
  }

  @Override
  public Account depositIntoAccount(Long accountId, Long amount) {
    final Account account = accountOutputPort.depositIntoAccount(accountId, amount);
    return account;
  }

  @Override
  public AccountQueryResult queryAccount(Long accountId) {
    return accountOutputPort.queryAccount(accountId);
  }

  @Override
  public boolean transferBetweenAccounts(Long fromAccountId, Long toAccountId, Long amount) {
    return accountOutputPort.transferBetweenAccounts(fromAccountId, toAccountId, amount);
  }
}
