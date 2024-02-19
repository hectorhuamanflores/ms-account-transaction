package com.bootcamp.nttdata.external;

import com.bootcamp.nttdata.model.dto.AccountByNumAccountRequest;
import com.bootcamp.nttdata.model.dto.AccountByNumAccountResponse;
import com.bootcamp.nttdata.model.dto.AccountUpdateForTrxRequest;
import com.bootcamp.nttdata.model.dto.AccountUpdateForTrxResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@FeignClient(name="ms-account")
public interface AccountClient {
    @PostMapping("/account/numAccount")
    Mono<AccountByNumAccountResponse> getAccountById(@RequestBody AccountByNumAccountRequest accountByNumAccountRequest);

    @PutMapping("/account/updateAccountTrx")
    Mono<AccountUpdateForTrxResponse> updatePayment(@RequestBody AccountUpdateForTrxRequest trx);

}
