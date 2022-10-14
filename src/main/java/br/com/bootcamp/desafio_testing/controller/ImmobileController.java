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

    @GetMapping("/{id}/biggest-room")
    public ResponseEntity<Room> getBiggestRoom(@PathVariable long id) {
        return new ResponseEntity<>(service.getBiggestRoom(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/all-rooms")
    public ResponseEntity<List<RoomDTO>> getAllRoomArea(@PathVariable long id) {
        return new ResponseEntity<>(service.getAllRoomArea(id), HttpStatus.OK);
    }

    @GetMapping("/totalPrice")
    public ResponseEntity<BigDecimal> getTotalPrice(@RequestParam long immobileId){
        return new ResponseEntity<>(service.getPrice(immobileId), HttpStatus.OK);
    }

    @GetMapping("/area/{id}")
    public ResponseEntity<ImmobileDTO> getImmobileTotalArea(@PathVariable long id) throws NotFoundException {
        ImmobileDTO immobile = service.getImmobileTotalArea(id);
        return  new ResponseEntity<ImmobileDTO>(immobile, HttpStatus.OK);
    }
}
