package com.palm.practical.adapters.in.rest.data;

import com.palm.practical.domain.models.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AccountQueryResponse {
  private Long balance;
  private List<Transaction> transactions;
}
