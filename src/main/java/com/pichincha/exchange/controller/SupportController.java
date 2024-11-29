package com.pichincha.exchange.controller;

import com.pichincha.exchange.model.dto.request.ExchangeRegisterDTO;
import com.pichincha.exchange.model.dto.request.OperationRequestDTO;
import com.pichincha.exchange.persistence.entity.Exchange;
import com.pichincha.exchange.persistence.entity.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;
import service.SupportService;

@RestController
@RequestMapping(value = "/supportApi")
@RequiredArgsConstructor
public class SupportController {

    private final SupportService supportService;

    @GetMapping("/find")
    public Mono<Exchange> getRate(
            @RequestParam(name = "monedaOrigen") String monedaOrigen,
            @RequestParam(name = "monedaDestino") String monedaDestino
    ){
        return supportService.getExchange(Exchange.builder().
                moneda_destino(monedaDestino).moneda_origen(monedaOrigen).build());
    }

    @PostMapping(value = "/registerExchange")
    public Mono<Void> registerExchange(@RequestBody ExchangeRegisterDTO exchange){
        return supportService.saveExchange(exchange);
    }

    @PostMapping(value = "/updateExchange")
    public Mono<Void> updateExchange(@RequestBody ExchangeRegisterDTO exchange){
        return supportService.updateExchange(exchange);
    }

    @PostMapping(value = "/saveOperation")
    public Mono<Operation> saveOperation(@RequestBody OperationRequestDTO operation){
        return supportService.saveOperation(operation);
    }
}
