package com.pichincha.exchange.service.impl;

import com.pichincha.exchange.exception.ExchangeExistsException;
import com.pichincha.exchange.exception.ExchangeNotFoundException;
import com.pichincha.exchange.model.dto.request.ExchangeRegisterDTO;
import com.pichincha.exchange.model.dto.request.OperationRequestDTO;
import com.pichincha.exchange.persistence.entity.Audit;
import com.pichincha.exchange.persistence.entity.Exchange;
import com.pichincha.exchange.persistence.entity.Operation;
import com.pichincha.exchange.persistence.repository.AuditRepository;
import com.pichincha.exchange.persistence.repository.ExchangeRepository;

import com.pichincha.exchange.persistence.repository.OperationsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import service.SupportService;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class SupportServiceImpl implements SupportService {
    @Autowired
    private AuditRepository auditRepository;
    @Autowired
    private ExchangeRepository exchangeRepository;
    @Autowired
    private OperationsRepository operationsRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Mono<Operation> saveOperation(OperationRequestDTO op) {

        Operation operation = modelMapper.map(op, Operation.class);
        Audit.AuditBuilder audit = Audit.builder()
                .moneda_destino(operation.getMoneda_destino()).
                moneda_origen(operation.getMoneda_origen())
                .nombre_usuario(op.getNombre_usuario())
                .fecha(LocalDateTime.now()).
                tipo("Operación");
        Exchange e = Exchange.builder().moneda_origen(operation.getMoneda_origen())
                .moneda_destino(operation.getMoneda_destino()).build();
        return exchangeRepository.getExchange(e).switchIfEmpty(currencyNotFoundException(e))
                .map(p -> {
                    operation.setMonto_cambio(operation.getMonto().multiply(BigDecimal.valueOf(p.getTipo_cambio())));
                    operation.setTipo_cambio(p.getTipo_cambio());
                    return operation;
                })
                .zipWhen(ope -> {
                    audit.tipo_cambio(ope.getTipo_cambio());
                    return saveAudit(audit.build()).zipWith(operationsRepository.save(ope));
                }).map(Tuple2::getT1);
    }

    @Override
    public Mono<Void> saveAudit(Audit audit) {
        return auditRepository.save(audit)
                .doOnSuccess((s) -> System.out.println("Auditoría grabada correctamente"))
                .doOnError((e) -> System.out.println("Error: "+ e.getLocalizedMessage()))
                .then();
    }

    @Override
    public Mono<Void> saveExchange(ExchangeRegisterDTO exchange) {
        Exchange e = modelMapper.map(exchange, Exchange.class);
        return exchangeRepository.getExchange(e)
                .flatMap(ex -> Mono.error(new ExchangeExistsException("Ya existe un tipo de cambio para la combinación ingresada.", HttpStatus.BAD_REQUEST)))
                .switchIfEmpty(exchangeRepository.save(modelMapper.map(exchange, Exchange.class)))
                .then();
    }

    @Override
    public Mono<Object> updateExchange(ExchangeRegisterDTO exchange) {
        Exchange e = modelMapper.map(exchange, Exchange.class);
        return getExchange(e)
                .zipWhen((exc) -> exchangeRepository.update(e)
                        .doOnSuccess((s) -> {
                            System.out.println("Tipo de cambio  actualizado correctamente");
                            saveAudit(Audit.builder()
                                    .moneda_destino(exchange.getMoneda_destino()).
                                    moneda_origen(exchange.getMoneda_origen())
                                    //.nombre_usuario(exchange.getNombre_usuario())
                                    .fecha(LocalDateTime.now()).build());
                        })
                ).map(t -> Mono.just("Se cambió el tipo de cambio a ".concat(""+t.getT1().getTipo_cambio())));
    }

    @Override
    public Mono<Exchange> getExchange(Exchange exchange) {
        return exchangeRepository.getExchange(exchange)
                .switchIfEmpty(currencyNotFoundException(exchange));
    }

    @Override
    public Flux<Audit> listAudit() {
        return auditRepository.findAll();
    }

    public <T> Mono<T> currencyNotFoundException(Exchange exchange) {
        return Mono.error(new ExchangeNotFoundException(String.format("No existe tipo de cambio de %1$s a %2$s",
                exchange.getMoneda_origen(), exchange.getMoneda_destino()), HttpStatus.NOT_FOUND));
    }

}
