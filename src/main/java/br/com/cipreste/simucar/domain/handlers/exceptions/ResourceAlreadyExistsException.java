package br.com.cipreste.simucar.domain.handlers.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends BaseHttpException{

    public ResourceAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
    
}
