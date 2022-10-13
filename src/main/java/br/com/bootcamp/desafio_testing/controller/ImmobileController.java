package br.com.bootcamp.desafio_testing.controller;

import br.com.bootcamp.desafio_testing.dto.ImmobileDTO;
import br.com.bootcamp.desafio_testing.service.ImmobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/v1/immobile")
public class ImmobileController {

    @Autowired
    ImmobileService service;

    @GetMapping("/area/{immobileName}")
    public ResponseEntity<ImmobileDTO> getImmobile(@PathVariable String immobileName) throws ClassNotFoundException {
        ImmobileDTO immobile = service.getImmobile(immobileName);
        return  new ResponseEntity<ImmobileDTO>(immobile, HttpStatus.OK);
    }
}
