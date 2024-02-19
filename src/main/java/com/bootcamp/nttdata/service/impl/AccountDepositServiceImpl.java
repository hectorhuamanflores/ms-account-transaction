package com.bootcamp.nttdata.service.impl;


import java.util.function.Function;

import com.bootcamp.nttdata.external.AccountClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bootcamp.nttdata.model.dto.AccountByNumAccountRequest;
import com.bootcamp.nttdata.model.dto.AccountByNumAccountResponse;
import com.bootcamp.nttdata.model.dto.AccountDepositRequest;
import com.bootcamp.nttdata.model.dto.AccountUpdateForTrxRequest;
import com.bootcamp.nttdata.model.dto.AccountUpdateForTrxResponse;
import com.bootcamp.nttdata.model.AccountDeposit;
import com.bootcamp.nttdata.repository.AccountDepositRepository;
import com.bootcamp.nttdata.service.AccountDepositService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class AccountDepositServiceImpl implements AccountDepositService{
    @Autowired
    private AccountDepositRepository accountDepositRepository;

    //@Autowired
   // private  WebClient.Builder builder;

   /* @Autowired
    private  AccountClient accountClient;*/

    private WebClient creditServiceClient = WebClient.builder()
            .baseUrl("http://GATEWAY-SERVICE")
            .build();

    public Function<AccountByNumAccountRequest, Mono<AccountByNumAccountResponse>> msAccountbynumAccount = (objeto) -> creditServiceClient
            .post()
            .uri("/account/numAccount/")
            .body(Mono.just(objeto),AccountByNumAccountResponse.class)
            .retrieve()
            .bodyToMono(AccountByNumAccountResponse.class);

    public Function<AccountUpdateForTrxRequest, Mono<AccountUpdateForTrxResponse>> msAccountTrx = (objeto) -> creditServiceClient
            .put()
            .uri("/account/updateAccountTrx/")
            .body(Mono.just(objeto), AccountUpdateForTrxResponse.class)
            .retrieve()
            .bodyToMono(AccountUpdateForTrxResponse.class);


    @Override
    public Flux<AccountDeposit> getAllAccountDeposit() {
        return accountDepositRepository.findAll();
    }

    @Override
    public Mono<AccountDeposit> getAccountDepositById(String id) {
        return accountDepositRepository.findById(id);
    }

    @Override
    public Flux<AccountDeposit> getAccountDepositByIdAccount(String idAccount) {
        log.info("INICIO_ACCOUNT_DEPOSIT");
        log.info("idAccount: "+idAccount);
        return accountDepositRepository.findByIdAccount(idAccount);
    }

    @Override
    public Mono<AccountDeposit> createAccountDeposit(AccountDepositRequest deposit) {
        AccountByNumAccountRequest numAccount = AccountByNumAccountRequest.builder()
                .numAccount(deposit.getNumAccount())
                .build();
        log.error("aqui si entraaaaaaaaaaaaaaaaaaaaaaaaaa");
        //consultar  saldo
        Mono<AccountByNumAccountResponse> consultMsAccount = msAccountbynumAccount.apply(numAccount);
        //Mono<AccountByNumAccountResponse> consultMsAccount = accountClient.getAccountById(numAccount);
        return consultMsAccount.flatMap(result -> {
            double commssion;
            if(result.getMovementTrxMax() > result.getMovement()) {
                log.error("Sin comision");
                commssion = 0.0;
            }else{
                log.error("Con comision");
                commssion = result.getCommission();
            }

            AccountDeposit t = AccountDeposit.builder()
                    .idAccount(deposit.getNumAccount())
                    .tyTrxAccount(deposit.getTyTrxAccount())
                    .tyAccount(deposit.getTyAccount())
                    .currency(deposit.getCurrency())
                    .amountDeposit(deposit.getAmountDeposit())
                    .commission(commssion)
                    .amountTrx(deposit.getAmountDeposit()-commssion)
                    .dateStar(deposit.getDateStar())
                    .build();

            AccountUpdateForTrxRequest accountUpdateForTrx = AccountUpdateForTrxRequest.builder()
                    .numAccount(deposit.getNumAccount())
                    .type(deposit.getTyTrxAccount())
                    .amount(deposit.getAmountDeposit()-commssion)
                    .build();

            log.error("Entro al servicio Actualizar cuenta");
            Mono<AccountUpdateForTrxResponse> f = msAccountTrx.apply(accountUpdateForTrx);
            //Mono<AccountUpdateForTrxResponse> f = accountClient.updatePayment(accountUpdateForTrx);
            log.error("Salio del servicio Actualizar cuenta");
            log.error("Crea el nuevo deposito");
            return f.flatMap(ra ->accountDepositRepository.save(t));

        });

    }

    @Override
    public Mono<AccountDeposit> updateAccountDeposit(AccountDeposit accountDeposit) {

        return accountDepositRepository.findById(accountDeposit.getId())
                .flatMap( object ->{
                    object.setIdAccount(accountDeposit.getIdAccount());
                    object.setTyTrxAccount(accountDeposit.getTyTrxAccount());
                    object.setTyAccount(accountDeposit.getTyAccount());
                    object.setCurrency(accountDeposit.getCurrency());
                    object.setAmountDeposit(accountDeposit.getAmountDeposit());
                    object.setCommission(accountDeposit.getCommission());
                    object.setAmountTrx(accountDeposit.getAmountTrx());

                    return accountDepositRepository.save(object);
                });
    }

    @Override
    public Mono<AccountDeposit> deleteAccountDeposit(String id) {
        return accountDepositRepository.findById(id)
                .flatMap(existsAccountDepositRepository -> accountDepositRepository.delete(existsAccountDepositRepository)
                        .then(Mono.just(existsAccountDepositRepository)));
    }






}
