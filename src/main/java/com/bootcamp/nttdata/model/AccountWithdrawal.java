package com.bootcamp.nttdata.model;


import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(value = "account_withdrawal")
public class AccountWithdrawal {

    @Id
    private String  id;               // Identificador Credito Consumo
    private String  idAccount;        // Identificador de la cuenta (numero de cuenta)
    private Integer tyTrxAccount;     // 1:deposito  -1:retiro
    private Integer tyAccount;        // 1:ct.Ahorro - 2:ct.Corriente - 3:ct.PlazoFijo
    private Integer currency;         // tipo de moneda 1:soles
    private Double  amountWithdrawal;    // monto retiro
    private Double  commission;       // comision
    private Double  amountTrx;        // monto de la transferencia total
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateStar;        // Fecha de creacion

}