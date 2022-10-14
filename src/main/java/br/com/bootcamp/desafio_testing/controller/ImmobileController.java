package br.com.bootcamp.desafio_testing.controller;


import br.com.bootcamp.desafio_testing.dto.ImmobileDTO;
import br.com.bootcamp.desafio_testing.interfaces.IImmobileService;
import br.com.bootcamp.desafio_testing.model.Immobile;
import br.com.bootcamp.desafio_testing.dto.RoomDTO;
import br.com.bootcamp.desafio_testing.service.ImmobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import br.com.bootcamp.desafio_testing.model.Room;

import java.math.BigDecimal;


import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
    public BigDecimal getTotalPrice(@RequestParam long immobileId){
        return service.getPrice(immobileId);
    }

    @GetMapping("/area/{id}")
    public ResponseEntity<ImmobileDTO> getImmobile(@PathVariable long id) throws ClassNotFoundException {
        ImmobileDTO immobile = service.getImmobileTotalArea(id);
        return  new ResponseEntity<ImmobileDTO>(immobile, HttpStatus.OK);
    }
}
