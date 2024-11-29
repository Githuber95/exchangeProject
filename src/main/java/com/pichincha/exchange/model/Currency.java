package com.pichincha.exchange.model;

import lombok.Getter;

@Getter
public enum Currency {
    PEN("PEN"),
    USD("USD");

    private String code;
    Currency(String code){
        this.code = code;
    }


}
