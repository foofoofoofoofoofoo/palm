package com.palm.practical.adapters.in.rest.mapper;

import com.palm.practical.adapters.in.rest.data.AccountCreateRequest;
import com.palm.practical.adapters.in.rest.data.AccountCreateResponse;
import com.palm.practical.adapters.in.rest.data.AccountMoneyDepositRequest;
import com.palm.practical.adapters.in.rest.data.AccountMoneyDepositResponse;
import com.palm.practical.domain.models.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountRestMapper {
  Account toAccount(AccountCreateRequest accountCreateRequest);
  AccountCreateResponse toAccountCreateResponse(Account account);

  AccountMoneyDepositResponse toAccountMoneyDepositResponse(Account account);
}
