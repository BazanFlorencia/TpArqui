package com.integrador.service.mongodb;

import com.integrador.domain.mongodb.CuentaMongo;
import com.integrador.service.dto.cuenta.CuentaDTO;
import com.integrador.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.integrador.repository.mongodb.CuentaMongoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaServiceMongo {
    private final CuentaMongoRepository cuentaMongoRepository;

    public List<CuentaDTO> findAll(){
        return this.cuentaMongoRepository.findAll().stream().map(c -> new CuentaDTO(c.getId(),c.getSaldo(),c.getFechaAlta(),c.isDisponible())).toList();
    }

    public String save(CuentaDTO request){
        final var cuenta= this.cuentaMongoRepository.save(new CuentaMongo(request.getId(), request.getSaldo(), request.getFechaAlta(), request.isDisponible()));
        return cuenta.getId();
    }

    public CuentaMongo findById(String id){
        return this.cuentaMongoRepository.findById(id).orElseThrow();
    }

    public void delete (String id){
        cuentaMongoRepository.delete(this.cuentaMongoRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("ID no encontrado" + id)
        ));
    }

    public CuentaMongo update(String id, CuentaDTO request){
        CuentaMongo cuenta= this.cuentaMongoRepository.findById(id).orElseThrow(
                () -> new NotFoundException("ID de cuenta invalido")
        );
        cuenta.setSaldo(request.getSaldo());
        cuenta.setFechaAlta(request.getFechaAlta());
        cuenta.setDisponible(request.isDisponible());

        return this.cuentaMongoRepository.save(cuenta);
    }

}
