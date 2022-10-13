package br.com.bootcamp.desafio_testing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class District {
    private String name;
    private BigDecimal valueDistrictM2;
}
