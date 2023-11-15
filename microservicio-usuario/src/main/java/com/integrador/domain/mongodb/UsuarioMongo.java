package com.integrador.domain.mongodb;

import com.integrador.service.dto.usuario.UsuarioDTO;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Usuario")
@Data
@NoArgsConstructor
public class UsuarioMongo {
    @Id
    private String id;
    private String nombre;
    private String apellido;
    private String celular;
    private String email;
    private List<CuentaMongo> cuentas;

    public UsuarioMongo(UsuarioDTO request) {
        this.id=request.getId();
        this.nombre = request.getNombre();
        this.apellido = request.getApellido();
        this.celular = request.getCelular();
        this.email = request.getEmail();
        this.cuentas= new ArrayList<>();
    }

    public UsuarioMongo(String id,String nombre, String apellido, String celular, String email) {
        this.nombre = nombre;
        this.id=id;
        this.apellido = apellido;
        this.celular = celular;
        this.email = email;
        this.cuentas = new ArrayList<>();
    }

    public void insertarCuenta(CuentaMongo c) {
        if(!cuentas.contains(c)) {
            cuentas.add(c);
        }
    }

    public boolean quitarCuenta(CuentaMongo c) {
        if(cuentas.contains(c)) {
            return cuentas.remove(c);
        }else {
            return false;
        }
    }
}
