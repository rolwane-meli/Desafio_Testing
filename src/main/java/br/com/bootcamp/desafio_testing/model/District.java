package br.com.bootcamp.desafio_testing.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class District {
    @NotNull
    @NotBlank(message = "O bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
    private String name;

    @NotNull
    @NotBlank(message = "O valor do metro quadrado não pode estar vazio.")
    @Size(max = 13, message = "O comprimento do preço do metro quadrado não pode exceder 13 caracteres.")
    private BigDecimal valueDistrictM2;
}
