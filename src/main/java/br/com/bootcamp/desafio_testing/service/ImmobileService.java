package br.com.bootcamp.desafio_testing.service;

import br.com.bootcamp.desafio_testing.exception.NotFoundException;
import br.com.bootcamp.desafio_testing.interfaces.IImmobileRepo;
import br.com.bootcamp.desafio_testing.interfaces.IImmobileService;
import br.com.bootcamp.desafio_testing.model.Immobile;
import br.com.bootcamp.desafio_testing.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ImmobileService implements IImmobileService {

    @Autowired
    private IImmobileRepo repo;

    @Override
    public BigDecimal getPrice(long immobileId) {
        Optional<Immobile> immobile = repo.getById(immobileId);
        BigDecimal totalPrice =  new BigDecimal(0);

        if(immobile.isEmpty())
            throw new NotFoundException("Imóvel do Id: " + immobile + " não encontrado");

        for (Room room: immobile.get().getRoomList()) {
            BigDecimal roomArea = new BigDecimal(calculateRoomArea(room));
            BigDecimal roomPrice = immobile.get().getDistrict().getValueDistrictM2().multiply(roomArea);

            totalPrice = totalPrice.add(roomPrice);
        }

        return totalPrice;
    }

    private double calculateRoomArea(Room room){
        return room.getLength() * room.getWidth();
    }
}
