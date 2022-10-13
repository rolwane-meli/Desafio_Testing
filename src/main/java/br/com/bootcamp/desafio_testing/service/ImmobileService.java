package br.com.bootcamp.desafio_testing.service;

import br.com.bootcamp.desafio_testing.dto.ImmobileDTO;
import br.com.bootcamp.desafio_testing.interfaces.IImmobileRepo;
import br.com.bootcamp.desafio_testing.interfaces.IImmobileService;
import br.com.bootcamp.desafio_testing.model.Immobile;
import br.com.bootcamp.desafio_testing.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImmobileService implements IImmobileService {

    @Autowired
    IImmobileRepo repo;
    @Override
    public ImmobileDTO getImmobile(String immobileName) throws ClassNotFoundException {
        Optional<Immobile> immobile = repo.getImmobile(immobileName);
        if (immobile.isEmpty()) {
            throw new ClassNotFoundException("Esse imóvel nao existe");
        }

        return new ImmobileDTO(immobile.get(),calculateTotalArea(immobile.get()));
    }

    private double calculateTotalArea(Immobile immobile){
       double totalArea = 0;

        for (Room room: immobile.getRoomList()) {
            totalArea += room.getWidth() * room.getLength();
        }
        return totalArea;
    }


}
