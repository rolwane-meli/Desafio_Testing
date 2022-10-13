package br.com.bootcamp.desafio_testing.interfaces;

import java.math.BigDecimal;

import br.com.bootcamp.desafio_testing.model.Immobile;
import br.com.bootcamp.desafio_testing.model.Room;

public interface IImmobileService {
    BigDecimal getPrice(long immobileId);
    Room getBiggestRoom(long idImmobile);
}
