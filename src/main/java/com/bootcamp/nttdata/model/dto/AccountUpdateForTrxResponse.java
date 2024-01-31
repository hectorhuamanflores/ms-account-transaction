package com.bootcamp.nttdata.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class AccountUpdateForTrxResponse {
	
	private String  numAccount;       // Numero de cuenta bancaria
    private Integer documentNumber;   // Dni - Ruc del cliente
    private String  tyAccount;        // ct.Ahorro - ct.Corriente - ct.PlazoFijo
    private String  tyCustomer;       // Persona - Empresarial (persona natural o juridica)
    private LocalDate     dateStar;   // Fecha de creacion
    private LocalDate     dateEnd;    // Fecha de caducidad
    private String condition;         // activo - inactivo
	private Double  balance;          // saldo de la cuenta bancaria
	
	

}
