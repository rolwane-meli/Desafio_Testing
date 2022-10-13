package br.com.bootcamp.desafio_testing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Immobile {
    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "O nome da propriedade deve começar com uma letra maiúscula.")
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres.")
    private String name;

    @NotNull
    private @Valid District district;

    @NotNull(message = "A lista de cômodos não pode ser nula.")
    @NotEmpty(message = "A lista de cômodos não pode ser vazia.")
    private List<@Valid Room> roomList;
}
