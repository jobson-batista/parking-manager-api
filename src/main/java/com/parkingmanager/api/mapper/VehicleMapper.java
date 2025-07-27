package com.parkingmanager.api.mapper;

import com.parkingmanager.api.dto.VehicleDTO;
import com.parkingmanager.api.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    VehicleDTO toDTO(Vehicle vehicle);

    Vehicle toEntity(VehicleDTO dto);

    List<VehicleDTO> toListVehicleDTO(List<Vehicle> vehicles);

    List<Vehicle> toListVehicle(List<VehicleDTO> vehicles);
}

