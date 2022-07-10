package com.palm.practical.adapters.out.persistence;

import com.palm.practical.adapters.out.persistence.entities.AccountEntity;
import com.palm.practical.adapters.out.persistence.mapper.AccountPersistenceMapper;
import com.palm.practical.adapters.out.persistence.repositories.AccountRepository;
import com.palm.practical.domain.models.Account;
import com.palm.practical.domain.models.Transaction;
import com.palm.practical.domain.records.AccountQueryResult;
import com.palm.practical.ports.out.AccountOutputPort;
import com.palm.practical.ports.out.TransactionOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountOutputPort {

  private final AccountRepository accountRepository;
  private final AccountPersistenceMapper accountPersistenceMapper;

  private final TransactionOutputPort transactionOutputPort;

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public Account depositIntoAccount(Long accountId, long amount) {
    final Optional<AccountEntity> possibleAccountEntity =
      accountRepository.findById(accountId);
    if (possibleAccountEntity.isEmpty()) {
      return null;
    }
    final AccountEntity accountEntity = possibleAccountEntity.get();
    accountEntity.setBalance(accountEntity.getBalance() + amount);
    transactionOutputPort.saveTransaction(Transaction.builder()
      .accountId(accountEntity.getId())
      .amount(amount)
      .ttype("DEPOSIT")
      .build());
    final Account account = saveAccount(accountPersistenceMapper.toAccount(accountEntity));
    return account;
  }

  @Override
  public AccountQueryResult queryAccount(Long accountId) {
    final Optional<AccountEntity> possibleAccountEntity =
      accountRepository.findById(accountId);
    if (possibleAccountEntity.isEmpty()) {
      return null;
    }
    final Account account = accountPersistenceMapper.toAccount(possibleAccountEntity.get());
    final List<Transaction> transactionList =
      transactionOutputPort.findAll(accountId, Sort.by(Sort.Direction.ASC, "id"));
    return new AccountQueryResult(account, transactionList);
  }

  @Override
  public Account saveAccount(Account account) {
    final AccountEntity accountEntity = accountPersistenceMapper.toAccountEntity(account);
    final AccountEntity savedAccountEntity = accountRepository.save(accountEntity);
    return accountPersistenceMapper.toAccount(savedAccountEntity);
  }

  @Override
  @Transactional
  public boolean transferBetweenAccounts(Long fromAccountId, Long toAccountId, Long amount) {
    if (fromAccountId.equals(toAccountId)) {
      return false;
    }
    final Optional<AccountEntity> possibleFromAccountEntity;
    final Optional<AccountEntity> possibleToAccountEntity;

    // acquire locks in specific order to avoid deadlock
    if (fromAccountId < toAccountId) {
      possibleFromAccountEntity = accountRepository.findById(fromAccountId);
      possibleToAccountEntity = accountRepository.findById(toAccountId);
    } else {
      possibleToAccountEntity = accountRepository.findById(toAccountId);
      possibleFromAccountEntity = accountRepository.findById(fromAccountId);
    }

    if (possibleFromAccountEntity.isEmpty() || possibleToAccountEntity.isEmpty()) {
      return false;
    }

    final AccountEntity fromAccountEntity = possibleFromAccountEntity.get();
    final AccountEntity toAccountEntity = possibleToAccountEntity.get();
    if (fromAccountEntity.getBalance() < amount) {
      return false;
    }

    fromAccountEntity.setBalance(fromAccountEntity.getBalance() - amount);
    toAccountEntity.setBalance(toAccountEntity.getBalance() + amount);
    saveAccount(accountPersistenceMapper.toAccount(fromAccountEntity));
    saveAccount(accountPersistenceMapper.toAccount(toAccountEntity));
    transactionOutputPort.saveTransaction(Transaction.builder()
      .accountId(fromAccountEntity.getId())
      .amount(-amount)
      .ttype("TRANSFER TO " + toAccountEntity.getId())
      .build());
    transactionOutputPort.saveTransaction(Transaction.builder()
      .accountId(toAccountEntity.getId())
      .amount(amount)
      .ttype("TRANSFER FROM " + fromAccountEntity.getId())
      .build());

    return true;
  }
}
