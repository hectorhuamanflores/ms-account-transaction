package com.bootcamp.nttdata.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountWithdrawalRequest {

    private String  idAccount;        // Identificador de la cuenta (numero de cuenta)
    private Integer tyTrxAccount;     // 1:deposito  -1:retiro
    private Integer tyAccount;        // 1:ct.Ahorro - 2:ct.Corriente - 3:ct.PlazoFijo
    private Integer currency;         // tipo de moneda 1:soles
    private Double  amountWithdrawal;    // monto deposito
    private LocalDate dateStar;        // Fecha de creacion
}
