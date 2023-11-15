package com.integrador.service.dto.usuario;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.integrador.domain.mongodb.CuentaMongo;
import com.integrador.domain.mongodb.UsuarioMongo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UsuarioResponseDto implements Serializable{

	private  String id;
    private  String nombre;
    private String apellido;
	private  String celular;
	private  String email;
	@JsonIgnore
	private  List<CuentaMongo> cuentas;
	
	
	
    
	public UsuarioResponseDto(UsuarioMongo u ) {
        this.id = u.getId();
        this.nombre = u.getNombre();
        this.apellido = u.getApellido();
        this.celular = u.getCelular();
        this.email = u.getEmail();
        
        
        this.cuentas = u.getCuentas();
	}



	@Override
	public String toString() {
		return "UsuarioResponseDto [apellido=" + apellido + ", cuentas=" + cuentas + "]";
	}
	
	
}
