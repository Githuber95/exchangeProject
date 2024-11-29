package com.pichincha.exchange.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Builder
@Table(value = "Exchange")
public class Exchange {
    @Id
    private Integer id;

    private String moneda_origen;

    private String moneda_destino;

    private Double tipo_cambio;


}
