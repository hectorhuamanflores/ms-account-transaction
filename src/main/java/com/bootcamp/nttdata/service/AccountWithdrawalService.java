package com.bootcamp.nttdata.service;

import com.bootcamp.nttdata.model.dto.AccountWithdrawalRequest;
import com.bootcamp.nttdata.model.AccountWithdrawal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountWithdrawalService {

    public Flux<AccountWithdrawal> getAllAccountWithdrawal();
    public Mono<AccountWithdrawal> getAccountWithdrawalById(String id);
    public Flux<AccountWithdrawal> getAccountWithdrawalByIdAccount(String idAccount);
    public Mono<AccountWithdrawal> createAccountWithdrawal(AccountWithdrawalRequest accountWithdrawal);
    public Mono<AccountWithdrawal> updateAccountWithdrawal(AccountWithdrawal accountWithdrawal);
    public Mono<AccountWithdrawal> deleteAccountWithdrawal(String id);


}
