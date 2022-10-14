package br.com.bootcamp.desafio_testing.service;

import br.com.bootcamp.desafio_testing.dto.ImmobileDTO;
import br.com.bootcamp.desafio_testing.exception.NotFoundException;
import br.com.bootcamp.desafio_testing.interfaces.IImmobileRepo;
import br.com.bootcamp.desafio_testing.interfaces.IImmobileService;
import br.com.bootcamp.desafio_testing.model.Immobile;
import br.com.bootcamp.desafio_testing.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ImmobileService implements IImmobileService {
    @Autowired
    private IImmobileRepo repo;
    @Override
    public Room getBiggestRoom(long idImmobile) {
        Optional<Immobile> immobile = repo.getById(idImmobile);
        List<Room> roomList = immobile.get().getRoomList();
        double area = 0;
        Room biggestRoom = null;

        for (Room r : roomList) {
            if (calculateRoomArea(r) >= area) {
                area = calculateRoomArea(r);
                biggestRoom = r;
            }
        }
        return biggestRoom;
    }

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

    @Override
    public ImmobileDTO getImmobileTotalArea(long id) throws NotFoundException {
        Optional<Immobile> immobile = repo.getById(id);
        if (immobile.isEmpty()) {
            throw new NotFoundException("Esse imóvel nao existe");
        }

        return new ImmobileDTO(immobile.get(),calculateTotalArea(immobile.get()));
    }

    private double calculateTotalArea(Immobile immobile){
       double totalArea = 0;

        for (Room room: immobile.getRoomList()) {
            totalArea += this.calculateRoomArea(room);
        }
        return totalArea;
    }

    private double calculateRoomArea(Room room){
        return room.getLength() * room.getWidth();
    }
}
