package com.pichincha.exchange.service.impl;

import com.pichincha.exchange.exception.NoSuchUserException;
import com.pichincha.exchange.external.ExternalApi;
import com.pichincha.exchange.model.User;
import com.pichincha.exchange.model.dto.request.OperationRequestDTO;
import com.pichincha.exchange.model.dto.response.ExchangeResponse;
import com.pichincha.exchange.persistence.entity.Operation;
import com.pichincha.exchange.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;
import service.SupportService;

@Service
//@RequiredArgsConstructor
@Slf4j
public class ExperienceServiceImpl implements ExperienceService {
    @Autowired
    private SupportService supportService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExternalApi externalApi;

    @Override
    @Transactional
    public Mono<ExchangeResponse> generate(OperationRequestDTO operation) {
        NoSuchUserException notFound = new NoSuchUserException("No se encuentra el usuario", HttpStatus.NOT_FOUND);

        return externalApi.getExternalUser(operation.getId_usuario()).subscribeOn(Schedulers.immediate()).
                switchIfEmpty(Mono.error(notFound)).//doOnSuccess()
        zipWhen(u -> {
                    operation.setNombre_usuario(u.getName());
                    return supportService.saveOperation(operation)
                            .map(op -> ExchangeResponse.builder().symbol(op.getMoneda_destino())
                                    .amount(op.getMonto_cambio()).build());
                }).map(Tuple2::getT2);
        /*
                map(u -> {
                    operation.setNombre_usuario(u.getName());
                    return supportService.saveOperation(operation)
                            .map(op -> ExchangeResponse.builder().symbol(op.getMoneda_destino())
                                    .amount(op.getMonto_cambio()).build());
                }).flatMap(o -> o);*/
    }
}
