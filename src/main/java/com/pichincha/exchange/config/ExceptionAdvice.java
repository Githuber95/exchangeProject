package com.pichincha.exchange.config;

import com.pichincha.exchange.exception.ExchangeExistsException;
import com.pichincha.exchange.exception.ExchangeNotFoundException;
import com.pichincha.exchange.exception.NoSuchUserException;
import com.pichincha.exchange.util.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = ExchangeNotFoundException.class)
    public Mono<ResponseEntity<ResponseMessage<Void>>> getException(ExchangeNotFoundException exception) {

        ResponseMessage<Void> msg = ResponseMessage.<Void>builder()
                .message(exception.getMessage())
                .build();

        return Mono.just(new ResponseEntity<>(msg,exception.getHttpStatus()));
    }


    @ExceptionHandler(value = NoSuchUserException.class)
    public Mono<ResponseEntity<ResponseMessage<Void>>> getException(NoSuchUserException exception) {

        ResponseMessage<Void> msg = ResponseMessage.<Void>builder()
                .message(exception.getMessage())
                .build();

        return Mono.just(new ResponseEntity<>(msg,exception.getHttpStatus()));
    }

    @ExceptionHandler(value = ExchangeExistsException.class)
    public Mono<ResponseEntity<ResponseMessage<Void>>> getException(ExchangeExistsException exception) {

        ResponseMessage<Void> msg = ResponseMessage.<Void>builder()
                .message(exception.getMessage())
                .build();

        return Mono.just(new ResponseEntity<>(msg,exception.getHttpStatus()));
    }
}
