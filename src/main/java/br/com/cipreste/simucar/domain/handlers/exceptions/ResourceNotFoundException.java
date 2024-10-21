package br.com.cipreste.simucar.domain.handlers.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseHttpException{

    public ResourceNotFoundException(String className, String message) {
        super(className,message, HttpStatus.NOT_FOUND);
    }
    
}
