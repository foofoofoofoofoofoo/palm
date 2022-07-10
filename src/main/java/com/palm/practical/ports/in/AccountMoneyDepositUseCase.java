package com.palm.practical.ports.in;

import com.palm.practical.domain.models.Account;

public interface AccountMoneyDepositUseCase {
  Account depositIntoAccount(Long accountId, Long amount);
}
