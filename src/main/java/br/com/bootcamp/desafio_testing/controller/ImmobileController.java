package br.com.bootcamp.desafio_testing.controller;

import br.com.bootcamp.desafio_testing.interfaces.IImmobileService;
import br.com.bootcamp.desafio_testing.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/immobile")
public class ImmobileController {
    @Autowired
    private IImmobileService service;
    @GetMapping("/{id}/biggest-room")
    public Room getBiggestRoom(@PathVariable long id) {
        return service.getBiggestRoom(id);
    }

    @GetMapping("/totalPrice")
    public BigDecimal getTotalPrice(@RequestParam long immobileId){
        return service.getPrice(immobileId);
    }

}
