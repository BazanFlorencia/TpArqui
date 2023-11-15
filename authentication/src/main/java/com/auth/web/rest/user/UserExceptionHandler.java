package com.auth.web.rest.user;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth.service.exception.user.ErrorDTO;
import com.auth.service.exception.user.UserException;

@RestControllerAdvice( basePackages = "com.auth.web.rest.user" )
public class UserExceptionHandler {

    @ExceptionHandler( UserException.class )
    public ErrorDTO getUserException(UserException ex ){
        return new ErrorDTO( ex.getCode(), ex.getMessage() );
    }
}