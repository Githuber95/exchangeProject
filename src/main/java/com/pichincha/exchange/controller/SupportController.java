package com.pichincha.exchange.controller;

import com.pichincha.exchange.model.dto.request.ExchangeRegisterDTO;
import com.pichincha.exchange.model.dto.request.OperationRequestDTO;
import com.pichincha.exchange.persistence.entity.Audit;
import com.pichincha.exchange.persistence.entity.Exchange;
import com.pichincha.exchange.persistence.entity.Operation;
import com.pichincha.exchange.service.SupportService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @GetMapping("/listAudit")
    public Flux<Audit> getRate(
    ){
        return supportService.listAudit();
    }

    @PostMapping(value = "/registerExchange")
    public Mono<Void> registerExchange(@RequestBody ExchangeRegisterDTO exchange){
        return supportService.saveExchange(exchange);
    }

    @PostMapping(value = "/updateExchange")
    public Mono<Object> updateExchange(@RequestBody ExchangeRegisterDTO exchange){
        return supportService.updateExchange(exchange);
    }

    @PostMapping(value = "/saveOperation")
    public Mono<Operation> saveOperation(@RequestBody OperationRequestDTO operation){
        return supportService.saveOperation(operation);
    }
}
