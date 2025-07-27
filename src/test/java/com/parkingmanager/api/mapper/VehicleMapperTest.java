package com.parkingmanager.api.mapper;

import com.parkingmanager.api.dto.VehicleDTO;
import com.parkingmanager.api.enums.VehicleType;
import com.parkingmanager.api.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VehicleMapperTest {

    @Autowired
    private VehicleMapper vehicleMapper;

    @Test
    public void testToEntity() {
        // Create a VehicleDTO
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setBrand("Toyota");
        vehicleDTO.setModel("Corolla");
        vehicleDTO.setColor("Black");
        vehicleDTO.setNumberPlate("ABC1234");
        vehicleDTO.setType(VehicleType.CAR);

        // Convert to Vehicle entity
        Vehicle vehicle = vehicleMapper.toEntity(vehicleDTO);

        // Print the vehicle to verify it's not null
        System.out.println("[DEBUG_LOG] Vehicle: " + vehicle);
        System.out.println("[DEBUG_LOG] Brand: " + vehicle.getBrand());
        System.out.println("[DEBUG_LOG] Model: " + vehicle.getModel());
        System.out.println("[DEBUG_LOG] Color: " + vehicle.getColor());
        System.out.println("[DEBUG_LOG] Number Plate: " + vehicle.getNumberPlate());
        System.out.println("[DEBUG_LOG] Type: " + vehicle.getType());
    }
}