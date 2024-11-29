package com.pichincha.exchange.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class NoSuchUserException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String message;
    private HttpStatus httpStatus;

    public NoSuchUserException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
