package br.com.cipreste.simucar.domain.handlers.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    
    private String className;
    private HttpStatus httpStatus;
    private String message;
    private long timestamp;
    public ErrorResponse(HttpStatus httpStatus, String message, long timestamp) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = timestamp;
    }

    
}
