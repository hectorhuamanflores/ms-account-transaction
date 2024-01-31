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

import com.bootcamp.nttdata.model.dto.AccountDepositByIdAccountRequest;
import com.bootcamp.nttdata.model.dto.AccountDepositByIdRequest;
import com.bootcamp.nttdata.model.dto.AccountDepositRequest;
import com.bootcamp.nttdata.model.AccountDeposit;
import com.bootcamp.nttdata.service.AccountDepositService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/accountDeposit")
public class AccountDepositController {

    private final AccountDepositService accountDepositService;

    @GetMapping
    public Mono<ResponseEntity<Flux<AccountDeposit>>>getAllAccountDeposit() {
        Flux<AccountDeposit> list=this.accountDepositService.getAllAccountDeposit();
        return  Mono.just(ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(list))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/idAccountDeposit")
    public Mono<ResponseEntity<AccountDeposit>> getAccountDepositById(@RequestBody AccountDepositByIdRequest accountDepositByIdRequest){
        var Account=this.accountDepositService.getAccountDepositById(accountDepositByIdRequest.getIdAccountDeposit());
        return Account.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/idAccount")
    public Mono<ResponseEntity<Flux<AccountDeposit>>> getAccountDepositByIdAccount(@RequestBody AccountDepositByIdAccountRequest accountDepositByIdAccountRequest){

        Flux<AccountDeposit> list=this.accountDepositService.getAccountDepositByIdAccount(accountDepositByIdAccountRequest.getIdAccount());
        return  Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(list));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AccountDeposit> create(@RequestBody AccountDepositRequest Account){
        return this.accountDepositService.createAccountDeposit(Account);
    }

    @PutMapping("/updateAccountDeposit")
    public Mono<ResponseEntity<AccountDeposit>> updateAccount(@RequestBody AccountDeposit account){

        return this.accountDepositService.updateAccountDeposit(account)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteAccountById(@PathVariable String id){
        return this.accountDepositService.deleteAccountDeposit(id)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
