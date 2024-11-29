package service;

import com.pichincha.exchange.model.dto.request.ExchangeRegisterDTO;
import com.pichincha.exchange.model.dto.request.OperationRequestDTO;
import com.pichincha.exchange.persistence.entity.Audit;
import com.pichincha.exchange.persistence.entity.Exchange;
import com.pichincha.exchange.persistence.entity.Operation;
import reactor.core.publisher.Mono;

public interface SupportService {
    Mono<Operation> saveOperation(OperationRequestDTO operation);
    Mono<Void> saveAudit(Audit audit);
    Mono<Void> saveExchange(ExchangeRegisterDTO exchange);
    Mono<Void> updateExchange(ExchangeRegisterDTO exchange);
    Mono<Exchange> getExchange(Exchange exchange);
}
