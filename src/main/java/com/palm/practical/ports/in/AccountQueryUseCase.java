package com.palm.practical.ports.in;

import com.palm.practical.domain.records.AccountQueryResult;

public interface AccountQueryUseCase {
  AccountQueryResult queryAccount(Long accountId);
}
