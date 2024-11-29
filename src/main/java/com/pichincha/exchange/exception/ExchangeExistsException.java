package com.pichincha.exchange.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExchangeExistsException extends RuntimeException{
    private static final long serialVersionUID = 17L;
    private String message;
    private HttpStatus httpStatus;

    public ExchangeExistsException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return message;
    }
}
