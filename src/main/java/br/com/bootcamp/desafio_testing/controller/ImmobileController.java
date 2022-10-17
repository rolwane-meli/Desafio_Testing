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

    @GetMapping("/biggest-room")
    public ResponseEntity<Room> getBiggestRoom(@RequestParam long immobileId) {
        return new ResponseEntity<>(service.getBiggestRoom(immobileId), HttpStatus.OK);
    }

    @GetMapping("/all-rooms-area")
    public ResponseEntity<List<RoomDTO>> getAllRoomArea(@RequestParam long immobileId) {
        return new ResponseEntity<>(service.getAllRoomArea(immobileId), HttpStatus.OK);
    }

    @GetMapping("/total-price")
    public ResponseEntity<BigDecimal> getTotalPrice(@RequestParam long immobileId){
        return new ResponseEntity<>(service.getPrice(immobileId), HttpStatus.OK);
    }

    @GetMapping("/total-area")
    public ResponseEntity<ImmobileDTO> getImmobileTotalArea(@RequestParam long immobileId) throws NotFoundException {
        ImmobileDTO immobile = service.getImmobileTotalArea(immobileId);
        return  new ResponseEntity<ImmobileDTO>(immobile, HttpStatus.OK);
    }
}
