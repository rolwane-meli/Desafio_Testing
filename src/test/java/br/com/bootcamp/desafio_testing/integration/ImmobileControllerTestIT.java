package br.com.bootcamp.desafio_testing.integration;

import br.com.bootcamp.desafio_testing.service.ImmobileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
public class ImmobileControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ImmobileService service;

    @Autowired
    private ObjectMapper objectMapper;
}
