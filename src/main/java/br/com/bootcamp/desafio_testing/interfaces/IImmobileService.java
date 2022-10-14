package br.com.bootcamp.desafio_testing.interfaces;

import br.com.bootcamp.desafio_testing.dto.ImmobileDTO;
import java.math.BigDecimal;
import br.com.bootcamp.desafio_testing.exception.NotFoundException;
import java.util.List;

import br.com.bootcamp.desafio_testing.dto.RoomDTO;
import br.com.bootcamp.desafio_testing.model.Immobile;
import br.com.bootcamp.desafio_testing.model.Room;

public interface IImmobileService {
    BigDecimal getPrice(long immobileId);
    Room getBiggestRoom(long idImmobile);
    
    ImmobileDTO getImmobileTotalArea(long id) throws NotFoundException;
    List<RoomDTO> getAllRoomArea(long id);
}
