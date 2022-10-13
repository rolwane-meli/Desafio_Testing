package br.com.bootcamp.desafio_testing.controller;

import br.com.bootcamp.desafio_testing.interfaces.IImmobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/immobile")
public class ImmobileController {

    @Autowired
    private IImmobileService service;

    @GetMapping("/totalPrice")
    public BigDecimal getTotalPrice(@RequestParam long immobileId){
        return service.getPrice(immobileId);
    }

}
