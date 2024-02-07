package com.bootcamp.nttdata.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.nttdata.model.AccountWithdrawal;

import reactor.core.publisher.Flux;

@Repository
public interface AccountWithdrawalRepository  extends ReactiveCrudRepository<AccountWithdrawal,String> {
    /*
     * find(loQuetrae)By(loQueBusca)
     * findByNombreContainingOrApellidoContaining(String nombre,String apellido);
     *
     */
    Flux<AccountWithdrawal> findByIdAccount(String idAccount);

}
