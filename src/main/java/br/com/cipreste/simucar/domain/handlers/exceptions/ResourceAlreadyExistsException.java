package br.com.cipreste.simucar.domain.handlers.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends BaseHttpException{

    public ResourceAlreadyExistsException(String className, String message) {
        super(className,message, HttpStatus.CONFLICT);
    }
    
}
