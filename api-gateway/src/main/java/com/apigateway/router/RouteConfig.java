package com.apigateway.router;

import com.apigateway.security.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routes( RouteLocatorBuilder builder, AuthenticationFilter authFilter ) {
        return builder.routes()
                .route("lll", r -> r.path("/api/authenticate" )
                        .filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8081"))
                
                .route("auth-service", r -> r.path("/api/register")
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8081"))
                
                .route("microservicioMantAdm", r -> r.path( "/api/administrador/agregarMonopatin", "/api/administrador/eliminarMonopatin/**", "/api/administrador/paradas/agregarParada","/api/administrador/paradas/quitarParada/**", "/api/administrador/cuentas/anularCuenta/**", "/api/administrador/modificarTarifaEnFecha", "/api/administrador/definirTarifaEspecial/**")
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8004")) 
                .build();
    }

}
