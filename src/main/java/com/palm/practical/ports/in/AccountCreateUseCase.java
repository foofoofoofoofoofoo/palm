package com.palm.practical.ports.in;

import com.palm.practical.domain.models.Account;

public interface AccountCreateUseCase {
  Account createAccount(Account account);
}
