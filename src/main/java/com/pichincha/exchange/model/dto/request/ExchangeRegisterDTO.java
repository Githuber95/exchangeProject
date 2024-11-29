package com.pichincha.exchange.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExchangeRegisterDTO {

    @NotBlank(message = "Ingrese la moneda de origen")
    private String moneda_origen;
    @NotBlank(message = "Ingrese la moneda de destino")
    private String moneda_destino;
    @NotBlank(message = "Ingrese la moneda de origen")
    private Double tipo_cambio;

}
