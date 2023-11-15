package com.integrador.service.dto.usuarioCuenta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties( ignoreUnknown = true )
public class UsuarioCuentaRequestDto {
	
	private String idUsuario;
	private String idCuenta;
	
	public UsuarioCuentaRequestDto(String idUsuario, String idCuenta) {
		this.idUsuario = idUsuario;
		this.idCuenta = idCuenta;
	}

	
	
}
