package com.bootcamp.nttdata.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountByNumAccountResponse {
	
	private String    numAccount;
	private Integer   documentNumber;
	private String    tyAccount;        // ct.Ahorro - ct.Corriente - ct.PlazoFijo
    private String    tyCustomer;       // Persona - Empresarial (persona natural o juridica)
    private String    condition;        // activo - inactivo
    private String  subTyCustomer;    // VIP - PYME
    
    private Double commission;        // comision de mantenimiento
	private Integer movementTrxMax;   // limite maximo de movimientos mensuales
    private Integer movement;         // cantidad de movimientos actuales
}
