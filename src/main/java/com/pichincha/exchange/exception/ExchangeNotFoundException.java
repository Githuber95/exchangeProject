package com.pichincha.exchange.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExchangeNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 0L;
    private String message;
    private HttpStatus httpStatus;

    public ExchangeNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return message;
    }
}
