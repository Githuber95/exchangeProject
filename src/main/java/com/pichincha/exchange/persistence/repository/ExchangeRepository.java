package com.pichincha.exchange.persistence.repository;

import com.pichincha.exchange.persistence.entity.Exchange;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Repository
public interface ExchangeRepository extends ReactiveCrudRepository<Exchange, Integer> {

    //@Override
  //  <S extends Exchange> Mono<S> findOne(Example<S> example);
    @Query("UPDATE EXCHANGE SET tipo_cambio =:#{#cambio.tipo_cambio} WHERE" +
            " moneda_origen =:#{#cambio.moneda_origen} AND moneda_destino =:#{#cambio.moneda_destino}")
    Mono<Integer> update(@Param("cambio") Exchange cambio);

    @Query("SELECT e.moneda_destino, e.moneda_origen, e.tipo_cambio FROM EXCHANGE e WHERE moneda_origen =:#{#cambio.moneda_origen} AND" +
            " moneda_destino =:#{#cambio.moneda_destino} LIMIT 1")
    Mono<Exchange> getExchange(@Param("cambio") Exchange cambio);

    @Override
    <S extends Exchange> Mono<S> save(S entity);




}
