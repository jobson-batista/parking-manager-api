package com.parkingmanager.api.service;

import com.parkingmanager.api.dto.ParkingDTO;
import com.parkingmanager.api.exception.ParkingInvalidException;
import com.parkingmanager.api.mapper.ParkingMapper;
import com.parkingmanager.api.model.Address;
import com.parkingmanager.api.model.Parking;
import com.parkingmanager.api.repository.ParkingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkingServiceTest {

    @InjectMocks
    private ParkingService parkingService;

    @Mock
    private ParkingRepository parkingRepository;

    @Mock
    private ParkingMapper parkingMapper;

    @Test
    void whenAllFieldsNull_shouldThrowParkingInvalidException() {

        // ARRANGE
        ParkingDTO dto = new ParkingDTO();

        // ACT + ASSERT
        assertThrows(ParkingInvalidException.class, () -> parkingService.saveParking(dto));

    }

    @Test
    void whenAllFieldsOfTheDTOAreNotNull_shouldSaveParking() {

        // ARRANGE
        ParkingDTO dto = new ParkingDTO();
        dto.setName("Parking Shopping");
        dto.setCnpj("12345678000195");
        dto.setPhone("11987561234");
        dto.setAddress(new Address("street","12","Sao Paulo","SP","11908761"));
        dto.setMotorcycleVacancies(10);
        dto.setCarVacancies(10);

        Parking entity = new Parking();

        when(parkingMapper.toEntity(dto)).thenReturn(entity);

        // ACT + ASSERT
        assertDoesNotThrow(() -> parkingService.saveParking(dto));
        verify(parkingMapper).toEntity(dto);
        verify(parkingRepository).save(entity);
    }

    @Test
    void whenAllFieldsOfTheDTOAreNullWhenUpdating_shouldThrowParkingInvalidException() {

        // ARRANGE
        ParkingDTO dtoWithFieldsNull = new ParkingDTO();
        Parking entity = new Parking();

        // ACT
        when(parkingRepository.findById(1L)).thenReturn(Optional.of(entity));

        // ASSERT
        assertThrows(ParkingInvalidException.class, () -> parkingService.updateParkingById(1L, null));
        assertThrows(ParkingInvalidException.class, () -> parkingService.updateParkingById(1L, dtoWithFieldsNull));
    }

    @Test
    void whenAllFieldsOfTheDTOAreNotNullWhenUpdating_shouldUpdate() {

        // ARRANGE
        ParkingDTO dto = new ParkingDTO();
        dto.setName("Parking Shopping");
        dto.setCnpj("12345678000195");
        dto.setPhone("11987561234");
        dto.setAddress(new Address("street","12","Sao Paulo","SP","11908761"));
        dto.setMotorcycleVacancies(10);
        dto.setCarVacancies(10);

        Parking entity = new Parking();

        // ACT
        when(parkingRepository.findById(1L)).thenReturn(Optional.of(entity));

        // ASSERT
        assertDoesNotThrow(() -> parkingService.updateParkingById(1L, dto));
    }

    @Test
    void findAllParking_shouldReturnListOfParkingDTOs() {
        // ARRANGE
        List<Parking> parkingList = List.of(
                new Parking(1L, "Parking A", "123", "999999999", 10, 5, new Address(), false),
                new Parking(2L, "Parking B", "456", "888888888", 20, 15, new Address(), false)
        );

        List<ParkingDTO> parkingDTOList = List.of(
                new ParkingDTO(1L, "Parking A", "123", "999999999", 10, 5, null),
                new ParkingDTO(2L, "Parking B", "456", "888888888", 20, 15, null)
        );

        when(parkingRepository.findAll()).thenReturn(parkingList);
        when(parkingMapper.toDTOList(parkingList)).thenReturn(parkingDTOList);

        // ACT
        List<ParkingDTO> result = parkingService.findAllParking();

        // ASSERT
        assertEquals(2, result.size());
        assertEquals("Parking A", result.get(0).getName());
        verify(parkingRepository).findAll();
        verify(parkingMapper).toDTOList(parkingList);
    }

    @Test
    void findParkingById_shouldReturnParkingDTO_whenFound() {
        // ARRANGE
        Long id = 1L;
        Parking parking = new Parking(id, "Parking A", "123", "999999999", 10, 5, new Address(), false);
        ParkingDTO dto = new ParkingDTO(id, "Parking A", "123", "999999999", 10, 5, null);

        when(parkingRepository.findById(id)).thenReturn(Optional.of(parking));
        when(parkingMapper.toDTO(parking)).thenReturn(dto);

        // ACT
        ParkingDTO result = parkingService.findParkingById(id);

        // ASSERT
        assertEquals("Parking A", result.getName());
        verify(parkingRepository).findById(id);
        verify(parkingMapper).toDTO(parking);
    }

    @Test
    void whenParkingNotFound_sholdThrowParkingInvalidException() {
        // ARRANGE
        Long id = 999L;

        // ACT
        when(parkingRepository.findById(id)).thenReturn(Optional.empty());

        // ASSERT
        assertThrows(ParkingNotFound.class, () -> parkingService.findParkingById(id));
        verify(parkingRepository).findById(id);
        verifyNoInteractions(parkingMapper);

    }

}