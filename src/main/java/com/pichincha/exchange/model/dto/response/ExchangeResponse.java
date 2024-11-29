package com.pichincha.exchange.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExchangeResponse implements Serializable  {
    private static final long serialVersionUID = 7L;

    private String symbol;
    private BigDecimal amount;
}
