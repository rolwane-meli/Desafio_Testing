package br.com.bootcamp.desafio_testing.service;

import br.com.bootcamp.desafio_testing.dto.ImmobileDTO;
import br.com.bootcamp.desafio_testing.dto.RoomDTO;
import br.com.bootcamp.desafio_testing.exception.NotFoundException;
import br.com.bootcamp.desafio_testing.interfaces.IImmobileRepo;
import br.com.bootcamp.desafio_testing.model.District;
import br.com.bootcamp.desafio_testing.model.Immobile;
import br.com.bootcamp.desafio_testing.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ImmobileServiceTest {

    @InjectMocks
    private ImmobileService service;

    @Mock
    private IImmobileRepo repo;
    private Immobile immobile;
    private District district;

    private ImmobileDTO immobileDTO;

    private List<Room> room = new ArrayList<>();

    @BeforeEach
    void setup() {
        district = new District("Bairro teste",new BigDecimal(10000));
        room.add(new Room("Cozinha",5.0,4.0));
        room.add(new Room("Sala",5.0,4.0));
        immobile = new Immobile(1L,"Imovel teste",district, room);
    }
    @Test
    void getBiggestRoom_returnRoom_whenValidImmobile() {
        Mockito.when(repo.getById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(immobile));

        Room room = service.getBiggestRoom(1L);

        assertThat(room).isEqualTo(immobile.getRoomList().get(0));
        assertThat(room.getName()).isEqualTo(immobile.getRoomList().get(0).getName());
        assertThat(room.getLength()* room.getWidth()).isEqualTo(24.0);
    }

    @Test
    void getBiggestRoom_returnNotFoundException_whenImmobileEmpty() {
        Mockito.when(repo.getById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.getBiggestRoom(1L);
        });
    }

    @Test
    @DisplayName("Erro ao calcular o preço do imóvel")
    void getPrice_returnNotFoundException_whenThereIsNoImmobile() {
        Mockito.when(repo.getById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.getPrice(1);
        }).getMessage().equals("Imóvel do Id: 1 não encontrado");
    }

    @Test
    @DisplayName("Calcular o preço do imóvel")
    void getPrice_returnPrice_whenThereIsImmobile() {
        BigDecimal expected = new BigDecimal(400000);
        Mockito.when(repo.getById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(immobile));

        BigDecimal result = service.getPrice(1);

        assertThat(result).isNotNull();
        assertThat(result.doubleValue()).isNotZero();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getImmobileTotalArea_returnTotalArea_whenExistImmobile() throws NotFoundException {
        Mockito.when(repo.getById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(immobile));

        ImmobileDTO immobileDTO = service.getImmobileTotalArea(1L);

        assertThat(immobileDTO).isNotNull();
        assertThat(immobileDTO.getTotalArea()).isEqualTo(44.0);
    }
    @Test
    void getImmobileTotalArea_returnNotFoundException_whenThereIsNoImmobile() throws NotFoundException {
        long immobileID = 100;

        Mockito.when(repo.getById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.getImmobileTotalArea(immobileID);
        });
    }

    @Test
    void getAllRoomArea_returnAllRoomsArea_whenExistImmobile(){
        Mockito.when(repo.getById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(immobile));

        List<RoomDTO> roomDTO = service.getAllRoomArea(1L);

        assertThat(roomDTO).isNotNull();
        assertThat(roomDTO.get(0).getRoomArea()).isEqualTo(20);
        assertThat(roomDTO.size()).isEqualTo(2);
    }

    @Test
    void getAllRoomArea_returnNotFoundException_whenThereIsNoImmobile() throws NotFoundException {
        Mockito.when(repo.getById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.getAllRoomArea(100L);
        });
    }
}