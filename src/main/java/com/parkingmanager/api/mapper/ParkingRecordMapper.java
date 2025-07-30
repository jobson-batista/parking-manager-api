package com.parkingmanager.api.mapper;

import com.parkingmanager.api.dto.ParkingRecordDTO;
import com.parkingmanager.api.model.ParkingRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParkingRecordMapper {

    ParkingRecordDTO toDTO(ParkingRecord parking);

    ParkingRecord toEntity(ParkingRecordDTO dto);

    List<ParkingRecordDTO> toDTOList(List<ParkingRecord> parkingRecords);

}
