package br.com.bootcamp.desafio_testing.interfaces;

import br.com.bootcamp.desafio_testing.model.Immobile;

import java.util.Optional;

public interface IImmobileRepo {
    Optional<Immobile> getImmobile(String immobileName);
}
