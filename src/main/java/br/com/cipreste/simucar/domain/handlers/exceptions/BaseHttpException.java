package br.com.cipreste.simucar.domain.handlers.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class BaseHttpException extends RuntimeException {
    private final String className;
    private final LocalDateTime timestamp;
    private final HttpStatus httpStatus;

    public BaseHttpException(String message, String className, HttpStatus httpStatus) {
        super(message);
        this.className = className;
        this.timestamp = LocalDateTime.now();
        this.httpStatus = httpStatus;
    }


}
