package br.com.bootcamp.desafio_testing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Immobile {
    private String name;
    private District district;
    private List<Room> roomList;
}
