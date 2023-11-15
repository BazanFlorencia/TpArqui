package com.integrador.service.dto.cuenta;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import com.integrador.domain.mongodb.CuentaMongo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CuentaResponseDto {
	
	private  String id;
    private  double saldo;
    private Date fechaAlta;
    private  boolean disponible;
    
    public CuentaResponseDto(CuentaMongo c ) {
        this.id = c.getId();
        this.saldo = c.getSaldo();
        this.fechaAlta = c.getFechaAlta();
        this.disponible = c.isDisponible();
	}
	

}
