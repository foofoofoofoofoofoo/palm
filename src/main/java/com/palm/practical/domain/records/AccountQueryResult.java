package com.palm.practical.domain.records;

import com.palm.practical.domain.models.Account;
import com.palm.practical.domain.models.Transaction;

import java.util.List;

public record AccountQueryResult(Account account, List<Transaction> transactionList) {
}
