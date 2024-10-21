package br.com.cipreste.simucar.domain.handlers.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class BaseHttpException extends RuntimeException {

    private final LocalDateTime timestamp;
    private final HttpStatus httpStatus;

    public BaseHttpException(String message, HttpStatus httpStatus) {
        super(message);
        this.timestamp = LocalDateTime.now();
        this.httpStatus = httpStatus;
    }


}
