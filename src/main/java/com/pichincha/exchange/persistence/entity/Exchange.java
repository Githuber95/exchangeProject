package com.pichincha.exchange.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;


@Table(value = "Exchange")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Exchange implements Serializable {

    private static final long serialVersionUID = 13L;

    @Id
    private Integer id;

    private String moneda_origen;

    private String moneda_destino;

    private Double tipo_cambio;


}
