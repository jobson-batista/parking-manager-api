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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        // ACT
        assertDoesNotThrow(() -> parkingService.saveParking(dto));

        // ASSERT
        verify(parkingMapper).toEntity(dto);
        verify(parkingRepository).save(entity);
    }

}