package br.com.bootcamp.desafio_testing.controller;


import br.com.bootcamp.desafio_testing.dto.ImmobileDTO;
import br.com.bootcamp.desafio_testing.dto.RoomDTO;
import br.com.bootcamp.desafio_testing.exception.NotFoundException;
import br.com.bootcamp.desafio_testing.interfaces.IImmobileService;
import br.com.bootcamp.desafio_testing.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/immobile")
public class ImmobileController {
    @Autowired
    private IImmobileService service;

    /**
     * Método que retorna o maior cômodo de um imóvel
     * @param immobileId long
     * @return ResponseEntity - Room
     */
    @GetMapping("/biggest-room")
    public ResponseEntity<Room> getBiggestRoom(@RequestParam long immobileId) {
        return new ResponseEntity<>(service.getBiggestRoom(immobileId), HttpStatus.OK);
    }

    /**
     * Método que retorna a área de todos os cômodos de um imóvel
     * @param immobileId long
     * @return ResponseEntity - Lista de RoomDTO
     */
    @GetMapping("/all-rooms-area")
    public ResponseEntity<List<RoomDTO>> getAllRoomArea(@RequestParam long immobileId) {
        return new ResponseEntity<>(service.getAllRoomArea(immobileId), HttpStatus.OK);
    }

    /**
     * Método que retorna o preço total de um imóvel
     * @param immobileId long
     * @return ResponseEntity BigDecimal
     */
    @GetMapping("/total-price")
    public ResponseEntity<BigDecimal> getTotalPrice(@RequestParam long immobileId){
        return new ResponseEntity<>(service.getPrice(immobileId), HttpStatus.OK);
    }

    /**
     * Método que retorna a área total de um imóvel
     * @param immobileId long
     * @return ResponseEntity ImmobileDTO
     * @throws NotFoundException Lançada quando o imóvel não é encontrado
     */
    @GetMapping("/total-area")
    public ResponseEntity<ImmobileDTO> getImmobileTotalArea(@RequestParam long immobileId) throws NotFoundException {
        ImmobileDTO immobile = service.getImmobileTotalArea(immobileId);
        return  new ResponseEntity<ImmobileDTO>(immobile, HttpStatus.OK);
    }
}
