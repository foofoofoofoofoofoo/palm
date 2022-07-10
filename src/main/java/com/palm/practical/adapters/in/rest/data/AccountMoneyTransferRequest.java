package com.palm.practical.adapters.in.rest.data;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AccountMoneyTransferRequest {

  @NotNull(message = "Field 'fromAccountId' cannot be empty.")
  private Long fromAccountId;
  @NotNull(message = "Field 'toAccountId' cannot be empty.")
  private Long toAccountId;
  @NotNull(message = "Field 'amount' cannot be empty.")
  private Long amount;
}
