package br.com.bootcamp.desafio_testing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class District {
    @NotBlank(message = "O bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
    private String name;

    @NotNull(message = "O valor do metro quadrado não pode estar vazio.")
    @DecimalMax(value = "9999999999999", message = "O comprimento do preço do metro quadrado não pode exceder 13 caracteres.")
    private BigDecimal valueDistrictM2;
}
