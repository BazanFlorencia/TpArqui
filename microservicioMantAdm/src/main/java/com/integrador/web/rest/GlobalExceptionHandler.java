package com.integrador.web.rest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.integrador.service.dto.error.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( RuntimeException.class )
    public ErrorDTO get( RuntimeException ex ){
        return new ErrorDTO( ex.getMessage() );
    }
}

