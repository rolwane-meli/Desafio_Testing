package br.com.bootcamp.desafio_testing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Immobile {
    private long id;
    private String name;
    private District district;
    private List<Room> roomList;
}
