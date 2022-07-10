package com.palm.practical.adapters.out.persistence;

import com.palm.practical.adapters.out.persistence.entities.AccountEntity;
import com.palm.practical.adapters.out.persistence.entities.TransactionEntity;
import com.palm.practical.adapters.out.persistence.mapper.TransactionPersistenceMapper;
import com.palm.practical.adapters.out.persistence.repositories.TransactionRepository;
import com.palm.practical.domain.models.Transaction;
import com.palm.practical.ports.out.TransactionOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TransactionPersistenceAdapter implements TransactionOutputPort {

  private final TransactionRepository transactionRepository;
  private final TransactionPersistenceMapper transactionPersistenceMapper;

  @Override
  public List<Transaction> findAll(Long accountId, Sort sort) {
    final List<TransactionEntity> transactionEntityList =
      transactionRepository.findAll(
        Example.of(TransactionEntity.builder().accountId(accountId).build()),
        sort);
    return transactionEntityList
      .stream()
      .map(te -> transactionPersistenceMapper.toTransaction(te))
      .collect(Collectors.toList());
  }

  @Override
  public Transaction saveTransaction(Transaction transaction) {
    final TransactionEntity transactionEntity =
      transactionPersistenceMapper.toTransactionEntity(transaction);
    final TransactionEntity savedTransactionEntity =
      transactionRepository.save(transactionEntity);
    return transactionPersistenceMapper.toTransaction(savedTransactionEntity);
  }
}
