package com.palm.practical.adapters.in.rest;

import com.palm.practical.adapters.in.rest.data.*;
import com.palm.practical.adapters.in.rest.mapper.AccountRestMapper;
import com.palm.practical.domain.models.Account;
import com.palm.practical.domain.models.Transaction;
import com.palm.practical.domain.records.AccountQueryResult;
import com.palm.practical.ports.in.AccountCreateUseCase;
import com.palm.practical.ports.in.AccountMoneyDepositUseCase;
import com.palm.practical.ports.in.AccountMoneyTransferUseCase;
import com.palm.practical.ports.in.AccountQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class RestAccountAdapter {

  private final AccountCreateUseCase accountCreateUseCase;
  private final AccountMoneyDepositUseCase accountMoneyDepositUseCase;
  private final AccountMoneyTransferUseCase accountMoneyTransferUseCase;
  private final AccountRestMapper accountRestMapper;
  private final AccountQueryUseCase accountQueryUseCase;

  @PostMapping(value = "/")
  public ResponseEntity<AccountCreateResponse> createAccount(
    @RequestBody @Valid AccountCreateRequest accountCreateRequest
  ) {
    final Account account = accountRestMapper.toAccount(accountCreateRequest);
    final Account createdAccount = accountCreateUseCase.createAccount(account);
    return new ResponseEntity<>(accountRestMapper.toAccountCreateResponse(createdAccount), HttpStatus.CREATED);
  }

  @PostMapping(value = "/deposit")
  public ResponseEntity<AccountMoneyDepositResponse> depositIntoAccount(
    @RequestBody @Valid AccountMoneyDepositRequest accountMoneyDepositRequest
  ) {
    final Account account = accountMoneyDepositUseCase.depositIntoAccount(
      accountMoneyDepositRequest.getAccountId(), accountMoneyDepositRequest.getAmount());
    return new ResponseEntity<>(accountRestMapper.toAccountMoneyDepositResponse(account), HttpStatus.OK);
  }

  @GetMapping(value = "/{accountId}")
  public ResponseEntity<AccountQueryResponse> queryAccount(
    @PathVariable(value="accountId") Long accountId
  ) {
    final AccountQueryResult accountQueryResult = accountQueryUseCase.queryAccount(accountId);
    if (accountQueryResult == null) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "entity not found"
      );
    }
    final Account account = accountQueryResult.account();
    final List<Transaction> transactionList = accountQueryResult.transactionList();
    return new ResponseEntity<>(
      new AccountQueryResponse(account.getBalance(), transactionList),
      HttpStatus.OK
    );
  }

  @PostMapping(value = "/transfer")
  public ResponseEntity<AccountMoneyTransferResponse> transferBetweenAccounts(
    @RequestBody @Valid AccountMoneyTransferRequest accountMoneyTransferRequest
  ) {
    final boolean success = accountMoneyTransferUseCase.transferBetweenAccounts(
      accountMoneyTransferRequest.getFromAccountId(),
      accountMoneyTransferRequest.getToAccountId(),
      accountMoneyTransferRequest.getAmount());
    return new ResponseEntity<>(new AccountMoneyTransferResponse(success), HttpStatus.OK);
  }
}
