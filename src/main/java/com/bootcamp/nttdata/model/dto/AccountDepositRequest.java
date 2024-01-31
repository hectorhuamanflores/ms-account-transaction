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
public class AccountDepositRequest {
  
    private String  idAccount;        // Identificador de la cuenta (numero de cuenta)
    private Integer tyTrxAccount;     // 1:deposito  -1:retiro
    private Integer tyAccount;        // 1:ct.Ahorro - 2:ct.Corriente - 3:ct.PlazoFijo
    private Integer currency;         // tipo de moneda 1:soles
    private Double  amountDeposit;    // monto deposito
    private LocalDate dateStar;        // Fecha de creacion
}
