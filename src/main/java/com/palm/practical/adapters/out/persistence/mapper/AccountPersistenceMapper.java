package com.palm.practical.adapters.out.persistence.mapper;

import com.palm.practical.adapters.out.persistence.entities.AccountEntity;
import com.palm.practical.domain.models.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountPersistenceMapper {

  AccountEntity toAccountEntity(Account account);

  Account toAccount(AccountEntity accountEntity);
}
