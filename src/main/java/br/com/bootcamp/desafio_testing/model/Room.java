package br.com.bootcamp.desafio_testing.model;

import javax.validation.constraints.*;

public class Room {
    @NotNull(message = "O nome do cômodo é obrigatório.")
    @NotBlank(message = "O nome do cômodo não pode estar vazio.")
    @Pattern(regexp="([A-Z]|[0-9])[\\s|\\w]*$", message = "O nome do cômodo deve começar com uma letra maiúscula.")
    @Size(max = 30, message = "O comprimento do nome do cômodo não pode exceder 30 caracteres.")
    private String name;

    @NotNull(message = "A largura do cômodo é obrigatória.")
    @NotBlank(message = "A largura do cômodo não pode estar vazia.")
    @Max(value = 25, message = "A largura máxima permitida por cômodo é de 25 metros.")
    private double width;

    @NotNull(message = "O comprimento do cômodo é obrigatório.")
    @NotBlank(message = "O comprimento do cômodo não pode estar vazio.")
    @Max(value = 33, message = "O comprimento máximo permitido por cômodo é de 33 metros.")
    private double length;
}
