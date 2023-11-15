package com.integrador.controller;

import java.util.List;

import com.integrador.domain.mongodb.CuentaMongo;
import com.integrador.service.dto.cuenta.CuentaDTO;
import com.integrador.service.mongodb.CuentaServiceMongo;
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

import com.integrador.service.dto.cuenta.CuentaResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/cuentas")
@RequiredArgsConstructor
public class CuentaController {

	@Autowired
	private CuentaServiceMongo cuentaService;
	
	@GetMapping("")
    public List<CuentaDTO> findAll(){

        return this.cuentaService.findAll();
    }

	
	 @GetMapping("/{id}")
           public ResponseEntity<?>getById(@PathVariable String id){
	        try{
	            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.findById(id));
	        }catch (Exception e){
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Cuenta inexistente");
	        }

	   }

    //ver si funciona
    @PostMapping("")
    public ResponseEntity<?> save( @RequestBody @Validated CuentaDTO request ){
        try {
        	return ResponseEntity.status(HttpStatus.OK).body(cuentaService.save(request));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Ocurrio un error, revise los campos ingresados");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        try{
            this.cuentaService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la cuenta con el id: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar la cuenta con id: " + id +".\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }
    
    //chequear
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Validated CuentaDTO request) {
        try {
            CuentaMongo cuenta = cuentaService.update(id, request);
            CuentaResponseDto response = new CuentaResponseDto(cuenta);

            return ResponseEntity.status(HttpStatus.OK).body(response);
          
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la cuenta con el ID proporcionado.");
        }
    }
    
	
	
}
