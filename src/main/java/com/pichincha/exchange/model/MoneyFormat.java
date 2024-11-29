package com.pichincha.exchange.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;

public class MoneyFormat {

    private final static DecimalFormat df = new DecimalFormat("###,###,###.00");
    private final BigDecimal value;

    public MoneyFormat(BigDecimal value){
        this.value = value;
    }

    public String toString() {
        return df.format(value);
    }

    public static MoneyFormat of(BigDecimal value){
        return new MoneyFormat(value == null? BigDecimal.ZERO : value);
    }

    @Getter
    public enum MoneySymbol {
        DOLARES("USD","$"),
        SOLES("PEN","S/");
        private String code;
        private String symbol;
        MoneySymbol(String code, String symbol){
            this.code = code;
            this.symbol = symbol;
        }

        public static MoneySymbol of(String code){
            return Arrays.stream(values())
                    .filter(t -> t.code.equals(code)).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Moneda no válida"));
        }
    }
}
