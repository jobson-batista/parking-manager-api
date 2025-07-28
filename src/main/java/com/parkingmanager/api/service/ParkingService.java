package com.parkingmanager.api.service;

import com.parkingmanager.api.dto.ParkingDTO;
import com.parkingmanager.api.exception.ParkingInvalidException;
import com.parkingmanager.api.mapper.ParkingMapper;
import com.parkingmanager.api.model.Parking;
import com.parkingmanager.api.repository.ParkingRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;
    private final ParkingMapper parkingMapper;

    ParkingService(
            ParkingRepository parkingRepository,
            ParkingMapper parkingMapper
    ) {
        this.parkingRepository = parkingRepository;
        this.parkingMapper = parkingMapper;
    }

    public ParkingDTO saveParking(ParkingDTO parkingDTO) {
        validateParkingDTO(parkingDTO);
        Parking parkingSaved = parkingRepository.save(parkingMapper.toEntity(parkingDTO));
        return parkingMapper.toDTO(parkingSaved);
    }

    public void validateParkingDTO(ParkingDTO dto) {
        if(dto.getName() == null || dto.getName().isEmpty()) {
            throw new ParkingInvalidException("The field 'name' can not be null.");
        } else if(dto.getCnpj() == null || dto.getCnpj().isBlank()) {
            throw new ParkingInvalidException("The field 'cnpj' can not be null.");
        } else if(dto.getAddress() == null) {
            throw new ParkingInvalidException("The field 'address' can not be null.");
        } else if(dto.getPhone() == null || dto.getPhone().isBlank()) {
            throw new ParkingInvalidException("The field 'phone' can not be null.");
        } else if(dto.getCarVacancies() == null) {
            throw new ParkingInvalidException("The field 'carVacancies' can not be null.");
        } else if(dto.getMotorcycleVacancies() == null) {
            throw new ParkingInvalidException("The field 'motorcycleVacancies' can not be null.");
        }
    }

}
