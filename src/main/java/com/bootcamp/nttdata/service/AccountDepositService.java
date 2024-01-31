package com.bootcamp.nttdata.service;

import com.bootcamp.nttdata.model.dto.AccountDepositRequest;
import com.bootcamp.nttdata.model.AccountDeposit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountDepositService {

    public Flux<AccountDeposit> getAllAccountDeposit();
    public Mono<AccountDeposit> getAccountDepositById(String id);
    public Flux<AccountDeposit> getAccountDepositByIdAccount(String idAccount);
    public Mono<AccountDeposit> createAccountDeposit(AccountDepositRequest AccountDeposit);
    public Mono<AccountDeposit> updateAccountDeposit(AccountDeposit AccountDeposit);
    public Mono<AccountDeposit> deleteAccountDeposit(String id);


}
