package com.pichincha.exchange.service;

import com.pichincha.exchange.model.dto.request.OperationRequestDTO;
import com.pichincha.exchange.model.dto.response.ExchangeResponse;
import com.pichincha.exchange.persistence.entity.Operation;
import reactor.core.publisher.Mono;

public interface ExperienceService {
    Mono<ExchangeResponse> generate(OperationRequestDTO operation);
}
