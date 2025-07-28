package com.parkingmanager.api.service;

import com.parkingmanager.api.dto.ParkingDTO;
import com.parkingmanager.api.exception.ParkingInvalidException;
import com.parkingmanager.api.exception.ParkingNotFound;
import com.parkingmanager.api.mapper.ParkingMapper;
import com.parkingmanager.api.model.Parking;
import com.parkingmanager.api.model.Stats;
import com.parkingmanager.api.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ParkingDTO> findAllParking() {
        return parkingMapper.toDTOList(parkingRepository.findAll());
    }

    public ParkingDTO findParkingById(Long id) {
        Parking parking = parkingRepository.findById(id)
                .orElseThrow(() -> new ParkingNotFound(id));
        return parkingMapper.toDTO(parking);
    }

    public ParkingDTO updateParkingById(Long id, ParkingDTO dto) {
        Parking parking = parkingRepository.findById(id)
                .orElseThrow(() -> new ParkingNotFound(id));

        if (dto == null) {
            throw new ParkingInvalidException("DTO can not be null");
        }

        boolean hasValidField = false;

        if (dto.getName() != null && !dto.getName().isBlank()) {
            parking.setName(dto.getName());
            hasValidField = true;
        }

        if (dto.getCnpj() != null && !dto.getCnpj().isBlank()) {
            parking.setCnpj(dto.getCnpj());
            hasValidField = true;
        }

        if (dto.getAddress() != null) {
            parking.setAddress(dto.getAddress());
            hasValidField = true;
        }

        if (dto.getPhone() != null && !dto.getPhone().isBlank()) {
            parking.setPhone(dto.getPhone());
            hasValidField = true;
        }

        if (dto.getCarVacancies() != null) {
            if (dto.getCarVacancies() < 0) {
                throw new ParkingInvalidException("The field 'carVacancies' can not be less than zero.");
            }
            parking.setCarVacancies(dto.getCarVacancies());
            hasValidField = true;
        }

        if (dto.getMotorcycleVacancies() != null) {
            if (dto.getMotorcycleVacancies() < 0) {
                throw new ParkingInvalidException("The field 'motorcycleVacancies' can not be less than zero.");
            }
            parking.setMotorcycleVacancies(dto.getMotorcycleVacancies());
            hasValidField = true;
        }

        if (!hasValidField) {
            throw new ParkingInvalidException("No valid fields were provided for update.");
        }

        return parkingMapper.toDTO(parkingRepository.save(parking));
    }

    public void deleteParkingById(Long id) {
        parkingRepository.findById(id)
                .orElseThrow(() -> new ParkingNotFound(id));
        parkingRepository.deleteById(id);
    }

    public Stats getStatsByParking(Long id) {
        // TODO
        return new Stats("Parking Mock",10,9);
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
