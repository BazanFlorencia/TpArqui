package com.integrador.service.dto.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class UsuarioDTO {
    private String id;
    private String nombre;
    private String apellido;
    private String celular;
    private String email;

    public UsuarioDTO(String id, String nombre, String apellido, String celular, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.email = email;
    }
}
