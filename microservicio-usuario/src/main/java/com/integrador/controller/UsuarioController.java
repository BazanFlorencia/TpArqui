package com.integrador.controller;

import java.util.List;

import com.integrador.domain.mongodb.UsuarioMongo;
import com.integrador.service.dto.usuario.UsuarioDTO;
import com.integrador.service.mongodb.UsuarioServiceMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador.service.dto.monopatin.MonopatinesCercaResponseDto;
import com.integrador.service.dto.usuario.UsuarioResponseDto;
import com.integrador.service.dto.usuarioCuenta.UsuarioCuentaRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	@Autowired
	private UsuarioServiceMongo usuarioService;
	
	@GetMapping("")
    public ResponseEntity<?> findAll(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
			
		}catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

	
	 @GetMapping("/{id}")
	   public ResponseEntity<?> getById(@PathVariable String id){
	        try{
	            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
	        }catch (Exception e){
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Usuario inexistente");
	        }
	        
	  }

    //ver si funciona
    @PostMapping("")
    public ResponseEntity<?> save( @RequestBody @Validated UsuarioDTO request ){
        try {
        	return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(request));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Ocurrio un error, revise los campos ingresados");
        }
    }
    
  //ver si funciona
    @PostMapping("/asociarCuenta")
    public ResponseEntity<?> asociarCuenta( @RequestBody @Validated UsuarioCuentaRequestDto request ){
        try {
        	return ResponseEntity.status(HttpStatus.OK).body(usuarioService.asociarCuenta(request));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Ocurrio un error, revise los campos ingresados");
        }
    }
    
    @DeleteMapping("/quitarCuenta")
    public ResponseEntity<?> quitarCuenta(@RequestBody @Validated UsuarioCuentaRequestDto request){
        try{
            this.usuarioService.quitarCuenta(request);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.quitarCuenta(request));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. revise los campos ingresados");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        try{
            this.usuarioService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente usuario con el id: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo eliminar el usuario con id: " + id);
        }
    }
    
    //chequear
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Validated UsuarioDTO request) {
        try {
            UsuarioMongo usuario = usuarioService.update(id, request);
            UsuarioResponseDto response = new UsuarioResponseDto(usuario);

            return ResponseEntity.status(HttpStatus.OK).body(response);
          
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el usuario con el ID proporcionado.");
        }
    }
    
    @GetMapping("/obtenerMonopatinCerca/{latitud}/{longitud}")
    public List<MonopatinesCercaResponseDto> ObtenerMonopatinesCerca(@PathVariable double latitud, @PathVariable double longitud){
        try{
            return usuarioService.obtenerMonopatinesCerca(latitud, longitud);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    

}
