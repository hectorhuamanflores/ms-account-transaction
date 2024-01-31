package com.bootcamp.nttdata.repository;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.nttdata.model.AccountDeposit;

import reactor.core.publisher.Flux;

@Repository
public interface AccountDepositRepository  extends ReactiveCrudRepository<AccountDeposit,String> {
    /*
     * find(loQuetrae)By(loQueBusca)
     * findByNombreContainingOrApellidoContaining(String nombre,String apellido);
     *
     */
    Flux<AccountDeposit> findByIdAccount(String idAccount);

}

