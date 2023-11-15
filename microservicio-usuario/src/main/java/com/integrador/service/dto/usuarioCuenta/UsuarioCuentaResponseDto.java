package com.integrador.service.dto.usuarioCuenta;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//@Data
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UsuarioCuentaResponseDto {
	
	private String idUsuario;
	private String idCuenta;



	public UsuarioCuentaResponseDto(String idUsuario, String idCuenta) {
		this.idUsuario = idUsuario;
		this.idCuenta = idCuenta;
	}


}
