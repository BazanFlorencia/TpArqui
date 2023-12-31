package com.auth.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private Double saldo;

    @ManyToMany( fetch = FetchType.LAZY, mappedBy = "cuentas" )
    private Set<User> usuarios;

}
