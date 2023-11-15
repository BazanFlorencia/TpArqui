package com.integrador.service.dto.cuenta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
public class CuentaDTO {

    private String id;
    private double saldo;
    private Date fechaAlta;
    private boolean disponible;

    public CuentaDTO(String id, double saldo, Date fechaAlta,boolean disponible){
        this.id=id;
        this.saldo=saldo;
        this.fechaAlta=fechaAlta;
        this.disponible=disponible;
    }

}
