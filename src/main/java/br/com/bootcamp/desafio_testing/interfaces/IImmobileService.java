package br.com.bootcamp.desafio_testing.interfaces;

import br.com.bootcamp.desafio_testing.dto.ImmobileDTO;

public interface IImmobileService {
    ImmobileDTO getImmobile(String immobileName) throws ClassNotFoundException;
}
