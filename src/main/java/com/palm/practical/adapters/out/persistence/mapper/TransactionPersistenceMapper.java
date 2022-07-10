package com.palm.practical.adapters.out.persistence.mapper;

import com.palm.practical.adapters.out.persistence.entities.TransactionEntity;
import com.palm.practical.domain.models.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionPersistenceMapper {
  TransactionEntity toTransactionEntity(Transaction transaction);
  Transaction toTransaction(TransactionEntity transactionEntity);
}
