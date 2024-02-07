package com.bootcamp.nttdata.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountWithdrawalByIdRequest {
	
   private String idAccountWithdrawal;
   private Integer currency;
}
