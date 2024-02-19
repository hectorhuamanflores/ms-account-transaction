package com.bootcamp.nttdata.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.nttdata.model.dto.AccountWithdrawalByIdAccountRequest;
import com.bootcamp.nttdata.model.dto.AccountWithdrawalByIdRequest;
import com.bootcamp.nttdata.model.dto.AccountWithdrawalRequest;
import com.bootcamp.nttdata.model.AccountWithdrawal;
import com.bootcamp.nttdata.service.AccountWithdrawalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/accountWithdrawal")
public class AccountWithdrawalController {

    private final AccountWithdrawalService accountWithdrawalService;

    @GetMapping
    public Mono<ResponseEntity<Flux<AccountWithdrawal>>>getAllAccountWithdrawal() {
        Flux<AccountWithdrawal> list=this.accountWithdrawalService.getAllAccountWithdrawal();
        return  Mono.just(ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(list))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/idAccountWithdrawal")
    public Mono<ResponseEntity<AccountWithdrawal>> getAccountWithdrawalById(@RequestBody AccountWithdrawalByIdRequest accountWithdrawalByIdRequest){
        var Account=this.accountWithdrawalService.getAccountWithdrawalById(accountWithdrawalByIdRequest.getIdAccountWithdrawal());
        return Account.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/idAccount")
    public Mono<ResponseEntity<Flux<AccountWithdrawal>>> getAccountWithdrawalByIdAccount(@RequestBody AccountWithdrawalByIdAccountRequest accountWithdrawalByIdAccountRequest){

        Flux<AccountWithdrawal> list=this.accountWithdrawalService.getAccountWithdrawalByIdAccount(accountWithdrawalByIdAccountRequest.getIdAccount());
        return  Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(list));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AccountWithdrawal> create(@RequestBody AccountWithdrawalRequest Account){
        return this.accountWithdrawalService.createAccountWithdrawal(Account);
    }

    @PutMapping("/updateAccountWithdrawal")
    public Mono<ResponseEntity<AccountWithdrawal>> updateAccount(@RequestBody AccountWithdrawal account){

        return this.accountWithdrawalService.updateAccountWithdrawal(account)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteAccountById(@PathVariable String id){
        return this.accountWithdrawalService.deleteAccountWithdrawal(id)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
