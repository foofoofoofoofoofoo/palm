package com.palm.practical.ports.in;

public interface AccountMoneyTransferUseCase {

  boolean transferBetweenAccounts(Long fromAccountId, Long toAccountId, Long amount);

}
