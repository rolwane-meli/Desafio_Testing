package br.com.bootcamp.desafio_testing.controller;

import br.com.bootcamp.desafio_testing.dto.ImmobileDTO;
import br.com.bootcamp.desafio_testing.dto.RoomDTO;
import br.com.bootcamp.desafio_testing.interfaces.IImmobileService;
import br.com.bootcamp.desafio_testing.model.District;
import br.com.bootcamp.desafio_testing.model.Immobile;
import br.com.bootcamp.desafio_testing.model.Room;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImmobileController.class)
class ImmobileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IImmobileService service;

    @Mock
    private Immobile immobile;
    private District district;

    private ImmobileDTO immobileDTO;

    private List<Room> room = new ArrayList<>();
    private List<RoomDTO> roomDTOList = new ArrayList<>();

    BigDecimal mockResultPrice = new BigDecimal(400000);

    @BeforeEach
    void setup() {
        district = new District("Bairro teste",new BigDecimal(10000));
        room.add(new Room("Cozinha",5.0,4.0));
        room.add(new Room("Sala",5.0,4.0));
        immobile = new Immobile(1L,"Imovel teste",district, room);
        immobileDTO = new ImmobileDTO(immobile,40.0);

        roomDTOList.add(new RoomDTO(immobile.getRoomList().get(0)));
        roomDTOList.add(new RoomDTO(immobile.getRoomList().get(1)));
    }
    @Test
    void getBiggestRoom_returnRoom_whenExistImmobile() throws Exception {

        BDDMockito.when(service.getBiggestRoom(ArgumentMatchers.anyLong()))
                .thenReturn(room.get(0));

        ResultActions response = mockMvc.perform(
                get("/api/v1/immobile/biggest-room?immobileId=1")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(room.get(0).getName())))
                .andExpect(jsonPath("$.width", CoreMatchers.is(room.get(0).getWidth())))
                .andExpect(jsonPath("$.length", CoreMatchers.is(room.get(0).getLength())));
    }

    @Test
    @DisplayName("Testar path de calcular o preço total do imóvel")
    void getTotalPrice_returnPrice_whenExistImmobile() throws Exception {
        BigDecimal expected = new BigDecimal(400000);

        BDDMockito.when(service.getPrice(ArgumentMatchers.anyLong()))
                .thenReturn(mockResultPrice);

        ResultActions response = mockMvc.perform(
                get("/api/v1/immobile/total-price?immobileId=1")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(result -> {
                    assertThat(result.getResponse().getContentAsString()).isEqualTo(expected.toString());
                });
    }

    @Test
    void getImmobileTotalArea_returnTotalArea_whenExistImmobile() throws Exception {
        BDDMockito.when(service.getImmobileTotalArea(ArgumentMatchers.anyLong()))
                .thenReturn(immobileDTO);

        ResultActions response = mockMvc.perform(
                get("/api/v1/immobile/total-area?immobileId=1")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(immobileDTO.getName())))
                .andExpect(jsonPath("$.totalArea", CoreMatchers.is(immobileDTO.getTotalArea())));
    }
    @Test
    void getAllRoomArea_returnTotalAreaOfAllRooms_whenExistImmobile() throws Exception {
        BDDMockito.when(service.getAllRoomArea(ArgumentMatchers.anyLong()))
                .thenReturn(roomDTOList);

        ResultActions response = mockMvc.perform(
                get("/api/v1/immobile/all-rooms-area?immobileId=1")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(2)))
                .andExpect(jsonPath("$[0].roomName", CoreMatchers.is("Cozinha")))
                .andExpect(jsonPath("$[0].roomArea", CoreMatchers.is(20.0)))
                .andExpect(jsonPath("$[1].roomName", CoreMatchers.is("Sala")))
                .andExpect(jsonPath("$[1].roomArea", CoreMatchers.is(20.0)));
    }
}