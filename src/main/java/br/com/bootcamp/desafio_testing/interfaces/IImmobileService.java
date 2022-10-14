package br.com.bootcamp.desafio_testing.interfaces;

import br.com.bootcamp.desafio_testing.dto.ImmobileDTO;
import java.math.BigDecimal;
import java.util.List;

import br.com.bootcamp.desafio_testing.dto.RoomDTO;
import br.com.bootcamp.desafio_testing.model.Immobile;
import br.com.bootcamp.desafio_testing.model.Room;

public interface IImmobileService {
    BigDecimal getPrice(long immobileId);
    Room getBiggestRoom(long idImmobile);
    ImmobileDTO getImmobileTotalArea(long id) throws ClassNotFoundException;
    List<RoomDTO> getAllRoomArea(long id);
}
