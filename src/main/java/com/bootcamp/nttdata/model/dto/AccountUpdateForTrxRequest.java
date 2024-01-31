package com.bootcamp.nttdata.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountUpdateForTrxRequest {
	
	private String   numAccount; // numero de cuenta bancaria
	private Integer  type;     // type=1=deposito   type=-1=retiro
	private Double   amount;   // monto de la transaccion
}
