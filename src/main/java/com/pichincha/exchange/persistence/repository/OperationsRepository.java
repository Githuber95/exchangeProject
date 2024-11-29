package com.pichincha.exchange.persistence.repository;

import com.pichincha.exchange.persistence.entity.Operation;
import org.springframework.data.domain.Example;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface OperationsRepository extends R2dbcRepository<Operation, Integer> {


    @Override
    public <S extends Operation> Mono<S> findOne(Example<S> example) ;

    @Override
    @Modifying
    @Query("INSERT INTO OPERATION (id_usuario, monto, moneda_origen, moneda_destino, monto_cambio, tipo_cambio) VALUES "
            + "(:#{#op.id_usuario}, :#{#op.monto}, :#{#op.moneda_origen}, :#{#op.moneda_destino}, :#{#op.monto_cambio}, :#{#op.tipo_cambio})")
     Mono<Operation> save(Operation op);

}
