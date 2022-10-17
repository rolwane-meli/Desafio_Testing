package br.com.bootcamp.desafio_testing.integration;

import br.com.bootcamp.desafio_testing.service.ImmobileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    @DisplayName("Testa Retorno do endpoint Immobile Total Area")
    void getImmobileTotalArea_returnTotalArea_whenExistImmobile() throws Exception {

        ResultActions response = mockMvc.perform(
                get("/api/v1/immobile/total-area?immobileId=1")
                        .contentType(MediaType.APPLICATION_JSON) );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is("Imóvel 01")))
                .andExpect(jsonPath("$.totalArea", CoreMatchers.is(80.25)));
    }

    @Test
    @DisplayName("Testa o end point all-rooms-area")
    void getAllRoomArea_returnListWithAllRooms_whenExistImmobile() throws Exception {

        ResultActions response = mockMvc.perform(
                get("/api/v1/immobile/all-rooms-area?immobileId=1")
                        .contentType(MediaType.APPLICATION_JSON) );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(4)))
                .andExpect(jsonPath("$[0].roomName", CoreMatchers.is("Cozinha")))
                .andExpect(jsonPath("$[0].roomArea", CoreMatchers.is(22.0)))
                .andExpect(jsonPath("$[3].roomName", CoreMatchers.is("Sala")))
                .andExpect(jsonPath("$[3].roomArea", CoreMatchers.is(12.0)));
    }
}
