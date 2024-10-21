package br.com.cipreste.simucar.domain.handlers.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseHttpException{

    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
    
}
