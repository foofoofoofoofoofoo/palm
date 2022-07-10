package com.palm.practical.ports.out;

import com.palm.practical.domain.models.Account;
import com.palm.practical.domain.records.AccountQueryResult;

public interface AccountOutputPort {

  Account depositIntoAccount(Long accountId, long amount);

  AccountQueryResult queryAccount(Long accountId);

  Account saveAccount(Account account);

  boolean transferBetweenAccounts(Long fromAccountId, Long toAccountId, Long amount);
}
