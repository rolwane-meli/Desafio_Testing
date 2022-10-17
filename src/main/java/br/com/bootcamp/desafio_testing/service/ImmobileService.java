package br.com.bootcamp.desafio_testing.service;

import br.com.bootcamp.desafio_testing.dto.ImmobileDTO;
import br.com.bootcamp.desafio_testing.dto.RoomDTO;
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
import java.util.stream.Collectors;

@Service
public class ImmobileService implements IImmobileService {
    @Autowired
    private IImmobileRepo repo;

    /**
     * Método que encontra o maior cômodo de um imóvel
     * @param idImmobile long
     * @return Optional de Immobile
     */
    @Override
    public Room getBiggestRoom(long idImmobile) {
        Optional<Immobile> immobile = repo.getById(idImmobile);
        if(immobile.isEmpty()) throw new NotFoundException("Imovél nao encontrado");

        List<Room> roomList = immobile.get().getRoomList();
        double area = calculateRoomArea(roomList.get(0));;
        Room biggestRoom = null;

        for (Room r : roomList) {
            if (calculateRoomArea(r) >= area) {
                area = calculateRoomArea(r);
                biggestRoom = r;
            }
        }

        return biggestRoom;
    }

    /**
     * Método que calcula o preço total de um imóvel
     * @param immobileId long
     * @return BigDecimal
     */
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

    /**
     * Método que retorna a área total de um imóvel
     * @param id int
     * @return ImmobileDTO
     * @throws NotFoundException Lançada quando o imóvel não é encontrado
     */
    @Override
    public ImmobileDTO getImmobileTotalArea(long id) throws NotFoundException {
        Optional<Immobile> immobile = repo.getById(id);
        if (immobile.isEmpty()) {
            throw new NotFoundException("Esse imóvel nao existe");
        }

        return new ImmobileDTO(immobile.get(),calculateTotalArea(immobile.get()));
    }

    /**
     * Método que calcula a área total de um imóvel
     * @param immobile Objeto do tipo Immobile
     * @return double
     */
    private double calculateTotalArea(Immobile immobile){
       double totalArea = 0;

        for (Room room: immobile.getRoomList()) {
            totalArea += this.calculateRoomArea(room);
        }

        return totalArea;
    }

    /**
     * Método que calcula a área de um cômodo
     * @param room Objeto do tipo Room
     * @return double
     */
    private double calculateRoomArea(Room room){
        return room.getLength() * room.getWidth();
    }

    /**
     * Método que retorna a área de todos os cômodos de um imóvel
     * @param id int
     * @return Lista de RoomDTO
     * @throws NotFoundException Lançada quando o imóvel não é encontrado
     */
    @Override
    public List<RoomDTO> getAllRoomArea(long id) throws NotFoundException{
        Optional<Immobile> immobile = repo.getById(id);
        if (immobile.isEmpty()) {
            throw new NotFoundException("Esse imóvel nao existe");
        }
        List<RoomDTO> allRooms;
        List<Room> roomList = immobile.get().getRoomList();;

        allRooms = roomList.stream()
                .map(RoomDTO::new)
                .collect(Collectors.toList());

        return allRooms;
    }
}
