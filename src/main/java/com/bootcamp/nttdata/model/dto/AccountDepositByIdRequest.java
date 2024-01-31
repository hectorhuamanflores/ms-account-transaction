package com.bootcamp.nttdata.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDepositByIdRequest {
	
   private String idAccountDeposit;
   private Integer currency;
}
