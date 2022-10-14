package br.com.bootcamp.desafio_testing.interfaces;

import br.com.bootcamp.desafio_testing.dto.ImmobileDTO;
import java.math.BigDecimal;

import br.com.bootcamp.desafio_testing.exception.NotFoundException;
import br.com.bootcamp.desafio_testing.model.Immobile;
import br.com.bootcamp.desafio_testing.dto.RoomDTO;
import br.com.bootcamp.desafio_testing.exception.NotFoundException;
import br.com.bootcamp.desafio_testing.model.Room;

import java.math.BigDecimal;
import java.util.List;

public interface IImmobileService {
    BigDecimal getPrice(long immobileId);
    Room getBiggestRoom(long idImmobile);
    List<RoomDTO> getAllRoomArea(long id) throws NotFoundException;
    ImmobileDTO getImmobileTotalArea(long id) throws NotFoundException;
}
