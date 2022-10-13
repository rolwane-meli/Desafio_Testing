package br.com.bootcamp.desafio_testing.model;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

public class Immobile {
    @NotNull
    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp="([A-Z]|[0-9])[\\s|\\w]*$", message = "O nome da propriedade deve começar com uma letra maiúscula.")
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres.")
    private String name;

    @NotNull
    @NotEmpty
    private @Valid District district;
    private List<Room> roomList;
}
