package com.palm.practical.adapters.in.rest.data;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AccountMoneyDepositRequest {
  @NotNull(message = "Field 'accountId' cannot be empty.")
  private Long accountId;

  @NotNull(message = "Field 'amount' cannot be empty.")
  private Long amount;
}
