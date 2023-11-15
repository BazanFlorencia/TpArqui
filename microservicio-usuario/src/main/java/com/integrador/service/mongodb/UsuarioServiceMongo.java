package com.integrador.service.mongodb;

import com.integrador.domain.mongodb.CuentaMongo;
import com.integrador.domain.mongodb.UsuarioMongo;
import com.integrador.repository.mongodb.CuentaMongoRepository;
import com.integrador.repository.mongodb.UsuarioMongoRepository;
import com.integrador.service.dto.monopatin.MonopatinesCercaResponseDto;
import com.integrador.service.dto.usuario.UsuarioDTO;
import com.integrador.service.dto.usuarioCuenta.UsuarioCuentaRequestDto;
import com.integrador.service.dto.usuarioCuenta.UsuarioCuentaResponseDto;
import com.integrador.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceMongo {

    private RestTemplate restTemplate;
    private final UsuarioMongoRepository usuarioMongoRepository;
    private final CuentaMongoRepository cuentaMongoRepository;
    public List<UsuarioDTO> findAll(){
        return this.usuarioMongoRepository.findAll().stream().map(u -> new UsuarioDTO(u.getId(),u.getNombre(),u.getApellido(),u.getCelular(),u.getEmail())).toList();
    }

    public String save(UsuarioDTO request){
        final var usuario= this.usuarioMongoRepository.save(new UsuarioMongo(request.getId(),request.getNombre(),request.getApellido(),request.getCelular(),request.getEmail()));
        return usuario.getId();
    }

    public UsuarioMongo findById(String id){
        return this.usuarioMongoRepository.findById(id).orElseThrow();
    }

    public void delete(String id){
        usuarioMongoRepository.delete(this.usuarioMongoRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("ID de usuario invalido;" +id)
        ));
    }

    public UsuarioMongo update (String id, UsuarioDTO request){
        UsuarioMongo usuario= this.usuarioMongoRepository.findById(id).orElseThrow(
                () -> new NotFoundException("ID de usuario invalido: "+ id)
        );
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setEmail(request.getEmail());
        usuario.setCelular(request.getCelular());
        return this.usuarioMongoRepository.save(usuario);
    }

    public UsuarioCuentaResponseDto asociarCuenta(UsuarioCuentaRequestDto request){
        UsuarioMongo usuario = usuarioMongoRepository.findById(request.getIdUsuario())
                .orElseThrow(()-> new NotFoundException(String.format("no existe el usuario con el id:"+request.getIdUsuario())));

        CuentaMongo cuenta = cuentaMongoRepository.findById(request.getIdCuenta())
                .orElseThrow(()-> new NotFoundException(String.format("no existe la cuenta con id:"+ request.getIdCuenta())));

        usuario.insertarCuenta(cuenta);

        return new UsuarioCuentaResponseDto(request.getIdUsuario(), request.getIdCuenta());

    }

    public UsuarioCuentaResponseDto quitarCuenta(UsuarioCuentaRequestDto request){

        CuentaMongo cuenta = cuentaMongoRepository.findById(request.getIdCuenta())
                .orElseThrow( () -> new NotFoundException(String.format("No existe la cuenta con id %s", request.getIdCuenta() ) ) );

        UsuarioMongo usuario = usuarioMongoRepository.findById(request.getIdUsuario())
                .orElseThrow( () -> new NotFoundException(String.format("No existe el usuario con id %s", request.getIdUsuario() ) ) );

        usuario.quitarCuenta(cuenta);

        return new UsuarioCuentaResponseDto(request.getIdUsuario(), request.getIdCuenta());
    }

    public List<MonopatinesCercaResponseDto> obtenerMonopatinesCerca(double latitud, double longitud) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<MonopatinesCercaResponseDto>> response = restTemplate.exchange(
                "http://localhost:8003/api/monopatines/obtenerMonopatinesCerca/" +latitud+"/"+longitud,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<MonopatinesCercaResponseDto>>() {}
        );
        System.out.println(response);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return response.getBody();
    }
}
