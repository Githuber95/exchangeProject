package com.pichincha.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 3L;

    private Integer id;
    private String name;
}
