package com.palm.practical.ports.out;

import com.palm.practical.domain.models.Transaction;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TransactionOutputPort {

  List<Transaction> findAll(Long accountId, Sort sort);
  Transaction saveTransaction(Transaction transaction);
}
