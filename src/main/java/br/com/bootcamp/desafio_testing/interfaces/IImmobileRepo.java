package br.com.bootcamp.desafio_testing.interfaces;

import br.com.bootcamp.desafio_testing.model.Immobile;
import java.util.List;
import java.util.Optional;

public interface IImmobileRepo {
    List<Immobile> getAll();
    Optional<Immobile> getById(long id);
}
