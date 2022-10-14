package br.com.bootcamp.desafio_testing.dto;

import br.com.bootcamp.desafio_testing.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private String roomName;
    private Double roomArea;

    public RoomDTO(Room room){
        this.roomName = room.getName();
        this.roomArea = room.getLength() * room.getWidth();
    }
}
