package com.parkingmanager.api.mapper;

import com.parkingmanager.api.dto.ParkingDTO;
import com.parkingmanager.api.model.Parking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingMapper {

    ParkingDTO toDTO(Parking parking);

    Parking toEntity(ParkingDTO dto);
}
