package br.com.bootcamp.desafio_testing.controller;

import br.com.bootcamp.desafio_testing.interfaces.IImmobileService;
import br.com.bootcamp.desafio_testing.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/immobile")
public class ImmobileController {
    @Autowired
    private IImmobileService service;
    @GetMapping("/{id}/biggest-room")
    public ResponseEntity<Room> getBiggestRoom(@PathVariable long id) {
        return new ResponseEntity<>(service.getBiggestRoom(id), HttpStatus.OK);
    }

    @GetMapping("/totalPrice")
    public BigDecimal getTotalPrice(@RequestParam long immobileId){
        return service.getPrice(immobileId);
    }

}
