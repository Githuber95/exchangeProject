package com.pichincha.exchange.model.dto.request;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OperationRequestDTO {
    @NotEmpty(message = "Debe especificar el usuario")
    private Integer id_usuario;

    private String nombre_usuario;

    @NotEmpty(message = "Debe especificar el monto a cambiar")
    private BigDecimal monto;

    @NotEmpty(message = "Debe especificar la moneda de origen")
    private String moneda_origen;

    @NotEmpty(message = "Debe especificar el monto a cambiar")
    private String moneda_destino;
}
