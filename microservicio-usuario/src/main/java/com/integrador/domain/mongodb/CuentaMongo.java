package com.integrador.domain.mongodb;

import com.integrador.service.dto.cuenta.CuentaDTO;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Cuenta")
@Data
@NoArgsConstructor
    public class CuentaMongo {
        @Id
        private String id;
        private double saldo;
        private Date fechaAlta;
        private boolean disponible;

    public CuentaMongo(CuentaDTO request) {
        this.id=request.getId();
        this.saldo = request.getSaldo();
        this.fechaAlta = request.getFechaAlta();
        this.disponible = request.isDisponible();
    }

    public CuentaMongo ( String id, double saldo,Date fechaAlta, boolean disponible) {
        this.saldo = saldo;
        this.id=id;
        this.fechaAlta = fechaAlta;
        this.disponible = disponible;
    }
}
