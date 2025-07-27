package com.parkingmanager.api.service;

import com.parkingmanager.api.dto.VehicleDTO;
import com.parkingmanager.api.enums.VehicleType;
import com.parkingmanager.api.exception.VehicleNotFoundException;
import com.parkingmanager.api.exception.VehicleNotSavedException;
import com.parkingmanager.api.mapper.VehicleMapper;
import com.parkingmanager.api.model.Vehicle;
import com.parkingmanager.api.repository.VehicleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleMapper vehicleMapper;

    @Test
    @DisplayName("Should save vehicle successfully and return corresponding DTO")
    void saveVehicle_shouldSaveAndReturnDTO() {

        // ARRANGE
        VehicleDTO inputDto = new VehicleDTO();
        inputDto.setBrand("Toyota");
        inputDto.setModel("Corolla");
        inputDto.setColor("Prata");
        inputDto.setNumberPlate("ABC1234");
        inputDto.setType(VehicleType.CAR);

        Vehicle entity = new Vehicle();
        Vehicle vehicleSaved = new Vehicle();
        VehicleDTO vehicleExpectedDTO = new VehicleDTO();

        when(vehicleMapper.toEntity(inputDto)).thenReturn(entity);
        when(vehicleRepository.save(entity)).thenReturn(vehicleSaved);
        when(vehicleMapper.toDTO(entity)).thenReturn(vehicleExpectedDTO);

        // ACT
        VehicleDTO result = vehicleService.saveVehicle(inputDto);

        // ASSERT
        assertEquals(vehicleExpectedDTO, result);
        verify(vehicleMapper).toEntity(inputDto);
        verify(vehicleRepository).save(entity);
        verify(vehicleMapper).toDTO(vehicleSaved);
    }

    @Test
    @DisplayName("Should throw VehicleNotSavedException when an error occurs during saving")
    void saveVehicle_shouldThrowVehicleNotSavedException_whenExceptionOccurs() {
        // Arrange
        VehicleDTO dto = new VehicleDTO();
        when(vehicleMapper.toEntity(dto)).thenThrow(new RuntimeException("Simulated error"));

        // Act + Assert
        assertThrows(VehicleNotSavedException.class, () -> vehicleService.saveVehicle(dto));
        verify(vehicleMapper).toEntity(dto);
        verify(vehicleRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw VehicleNotFoundException when the id does not exist during removal")
    void deleteVehicle_shouldThrowVehicleNotFoundException_whenIdNotExist() {
        // Arrange
        Long id = null;

        // Act + Assert
        assertThrows(VehicleNotFoundException.class, () -> vehicleService.deleteVehicleById(id));
    }

    @Test
    @DisplayName("Should delete the vehicle and not return an exception")
    void deleteVehicle_shouldDelete() {
        // Arrange
        Optional<Vehicle> vehicleReturned = Optional.of(new Vehicle());
        when(vehicleRepository.findById(1L)).thenReturn(vehicleReturned);

        // Act + Assert
        assertDoesNotThrow(() -> vehicleService.deleteVehicleById(1L));
        verify(vehicleRepository).deleteById(1L);
    }

}