package com.palm.practical.adapters.config;

import com.palm.practical.adapters.out.persistence.AccountPersistenceAdapter;
import com.palm.practical.adapters.out.persistence.TransactionPersistenceAdapter;
import com.palm.practical.adapters.out.persistence.UserPersistenceAdapter;
import com.palm.practical.adapters.out.persistence.mapper.AccountPersistenceMapper;
import com.palm.practical.adapters.out.persistence.mapper.TransactionPersistenceMapper;
import com.palm.practical.adapters.out.persistence.mapper.UserPersistenceMapper;
import com.palm.practical.adapters.out.persistence.repositories.AccountRepository;
import com.palm.practical.adapters.out.persistence.repositories.TransactionRepository;
import com.palm.practical.adapters.out.persistence.repositories.UserRepository;
import com.palm.practical.domain.services.AccountService;
import com.palm.practical.domain.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public AccountPersistenceAdapter accountPersistenceAdapter(
    final AccountRepository accountRepository,
    final AccountPersistenceMapper accountPersistenceMapper,
    final TransactionPersistenceAdapter transactionPersistenceAdapter
    ) {
    return new AccountPersistenceAdapter(accountRepository, accountPersistenceMapper, transactionPersistenceAdapter);
  }

  @Bean
  public AccountService accountService(final AccountPersistenceAdapter accountPersistenceAdapter) {
    return new AccountService(accountPersistenceAdapter);
  }

  @Bean
  public TransactionPersistenceAdapter transactionPersistenceAdapter(
    final TransactionRepository transactionRepository,
    final TransactionPersistenceMapper transactionPersistenceMapper
  ) {
    return new TransactionPersistenceAdapter(transactionRepository, transactionPersistenceMapper);
  }

  @Bean
  public UserPersistenceAdapter userPersistenceAdapter(
    final UserRepository userRepository,
    final UserPersistenceMapper userPersistenceMapper
  ) {
    return new UserPersistenceAdapter(userRepository, userPersistenceMapper);
  }

  @Bean
  public UserService userService(final UserPersistenceAdapter userPersistenceAdapter) {
    return new UserService(userPersistenceAdapter);
  }
}
