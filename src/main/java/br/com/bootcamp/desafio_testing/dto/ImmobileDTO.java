package br.com.bootcamp.desafio_testing.dto;

import br.com.bootcamp.desafio_testing.model.Immobile;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class ImmobileDTO {
    private String name;
    private Double totalArea;
    public ImmobileDTO(Immobile immobile, double totalArea) {
        this.name = immobile.getName();
        this.totalArea = totalArea;
    }
}
