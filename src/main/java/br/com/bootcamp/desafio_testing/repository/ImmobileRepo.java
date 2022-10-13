package br.com.bootcamp.desafio_testing.repository;

import br.com.bootcamp.desafio_testing.exception.ReadFileException;
import br.com.bootcamp.desafio_testing.interfaces.IImmobileRepo;
import br.com.bootcamp.desafio_testing.model.Immobile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ImmobileRepo implements IImmobileRepo {
    private final String pathFile = "src/main/resources/immobiles.json";

    ObjectMapper mapper = new ObjectMapper();

    public Optional<Immobile> getImmobile(String immobileName) {
        List<Immobile> properties = null;

        try {
            properties = Arrays.asList(mapper.readValue(new File(pathFile), Immobile[].class));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        for (Immobile i : properties) {
            if (i.getName().equals(immobileName)) return Optional.of(i);
        }
    }

    @Override
    public List<Immobile> getAll() {
        List<Immobile> immobiles = null;

        try {
            immobiles = Arrays.asList(mapper.readValue(new File(pathFile), Immobile[].class));
        } catch (Exception ex) {
            throw new ReadFileException(ex.getMessage());
        }

        return immobiles;
    }

    @Override
    public Optional<Immobile> getById(long id) {
        List<Immobile> immobiles = this.getAll();

        Optional<Immobile> immobile = immobiles.stream().filter(i -> i.getId()==id).findFirst();

        if(immobile.isPresent())
            return immobile;

        return Optional.empty();
    }
}
