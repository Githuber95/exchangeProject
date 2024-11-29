package com.pichincha.exchange.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@Table(value = "Audit")
public class Audit {
    @Id
    private Integer id;
    private String nombre_usuario;
    private Double tipo_cambio;
    private LocalDateTime fecha;
    private String tipo;
    private String moneda_origen;
    private String moneda_destino;
}
