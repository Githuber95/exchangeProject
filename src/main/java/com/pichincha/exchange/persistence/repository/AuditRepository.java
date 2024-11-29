package com.pichincha.exchange.persistence.repository;

import com.pichincha.exchange.persistence.entity.Audit;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AuditRepository  extends R2dbcRepository<Audit,Integer> {
    @Override
    <S extends Audit> Mono<S> save(S entity);

}
