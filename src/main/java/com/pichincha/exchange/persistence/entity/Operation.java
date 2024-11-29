package com.pichincha.exchange.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.math.BigDecimal;


@Table(value = "Operation")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Operation implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    private Integer id;

    private BigDecimal monto;

    private Integer id_usuario;

    private String moneda_origen;

    private String moneda_destino;

    private BigDecimal monto_cambio;

    private Double tipo_cambio;


}
