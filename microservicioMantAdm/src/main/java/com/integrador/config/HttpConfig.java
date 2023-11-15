package com.integrador.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.integrador.security.AuthorityConstants;
import com.integrador.security.jwt.JwtFilter;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;


@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class HttpConfig {


    private final JwtParser jwtParser;
    private final String secret = "QJeKx+s7XIv1WbBlj7vJ9CD3Ozj1rB3qjlNZY9ofWKJSaBNBo5r1q9Rru/OWlYb+UHV1n4/LJl1OBYYZZ7rhJEnn5peyHCd+eLJfRdArE37pc+QDIsJlabQtR7tYRa+SnvGRyL01uZsK33+gezV+/GPXBnPTj8fOojDUzJiPAvE=";

    public HttpConfig() {
        final var keyBytes = Decoders.BASE64.decode(secret);
        final var key = Keys.hmacShaKeyFor( keyBytes );
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	 http
         .addFilterBefore( new JwtFilter( jwtParser ), UsernamePasswordAuthenticationFilter.class);
 http
         .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers(new AntPathRequestMatcher("/api/**")).permitAll()
            		.requestMatchers(new AntPathRequestMatcher("/administrador/agregarMonopatin")).hasAuthority( AuthorityConstants.ADMIN)
            		.requestMatchers(new AntPathRequestMatcher("/administrador/eliminarMonopatin/**")).hasAuthority( AuthorityConstants.ADMIN)
            		.requestMatchers(new AntPathRequestMatcher("/administrador/paradas/agregarParada")).hasAuthority( AuthorityConstants.ADMIN)
            		.requestMatchers(new AntPathRequestMatcher("/administrador/paradas/quitarParada/**")).hasAuthority( AuthorityConstants.ADMIN)
            		.requestMatchers(new AntPathRequestMatcher("/administrador/cuentas/anularCuenta/**")).hasAuthority( AuthorityConstants.ADMIN)
            		.requestMatchers(new AntPathRequestMatcher("/administrador/modificarTarifaEnFecha")).hasAuthority( AuthorityConstants.ADMIN)
            		.requestMatchers(new AntPathRequestMatcher("/administrador/definirTarifaComun/**")).hasAuthority( AuthorityConstants.ADMIN)
            		.requestMatchers(new AntPathRequestMatcher("/administrador/definirTarifaEspecial/**")).hasAuthority( AuthorityConstants.ADMIN)
            		.requestMatchers(new AntPathRequestMatcher("/administrador/facturacion")).hasAuthority( AuthorityConstants.ADMIN)
            		.requestMatchers(new AntPathRequestMatcher("/administrador/monopatines/porKm/**")).hasAuthority( AuthorityConstants.MANT)
            		.requestMatchers(new AntPathRequestMatcher("/mantemiento/agregarMonopatinAMantenimiento/**")).hasAuthority( AuthorityConstants.MANT)
            		.requestMatchers(new AntPathRequestMatcher("/mantemiento/finalizarMantenimiento/**")).hasAuthority( AuthorityConstants.MANT)
                    .anyRequest().authenticated()
           	);
            http
                    .anonymous(AbstractHttpConfigurer::disable)
                    .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
            http
                    .httpBasic( Customizer.withDefaults() );
            return http.build();
    }
}
