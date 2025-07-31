package com.parkingmanager.api.service;

import com.parkingmanager.api.dto.ParkingRecordDTO;
import com.parkingmanager.api.enums.ParkingStatus;
import com.parkingmanager.api.enums.VehicleType;
import com.parkingmanager.api.exception.BadRequestException;
import com.parkingmanager.api.exception.NotFoundException;
import com.parkingmanager.api.mapper.ParkingMapper;
import com.parkingmanager.api.mapper.ParkingRecordMapper;
import com.parkingmanager.api.mapper.VehicleMapper;
import com.parkingmanager.api.model.Parking;
import com.parkingmanager.api.model.ParkingRecord;
import com.parkingmanager.api.model.Vehicle;
import com.parkingmanager.api.repository.ParkingRecordRepository;
import com.parkingmanager.api.repository.ParkingRepository;
import com.parkingmanager.api.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParkingRecordService {

    private final ParkingRecordRepository parkingRecordRepository;
    private final ParkingRepository parkingRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingRecordMapper parkingRecordMapper;
    private final ParkingMapper parkingMapper;
    private final VehicleMapper vehicleMapper;

    public ParkingRecordService(
            ParkingRecordRepository parkingRecordRepository,
            ParkingRecordMapper parkingRecordMapper,
            ParkingRepository parkingRepository,
            VehicleRepository vehicleRepository,
            ParkingMapper parkingMapper,
            VehicleMapper vehicleMapper
    ) {
        this.parkingRecordRepository = parkingRecordRepository;
        this.parkingRecordMapper = parkingRecordMapper;
        this.parkingRepository = parkingRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingMapper = parkingMapper;
        this.vehicleMapper = vehicleMapper;
    }

    public List<ParkingRecordDTO> findAllParking() {
        return parkingRecordMapper.toDTOList(parkingRecordRepository.findParkingRecordsByDeletedFalse());
    }

    public ParkingRecordDTO findParkingById(Long id) {
        ParkingRecord parkingRecord = parkingRecordRepository.findParkingRecordByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException(String.format("The parking record with ID %d does not exist.", id)));
        return parkingRecordMapper.toDTO(parkingRecord);
    }

    public ParkingRecordDTO registerEntryVehicle(ParkingRecordDTO parkingRecordDTO) {

        validateParkingRecord(parkingRecordDTO);

        Parking parking = parkingRepository.findByIdAndDeletedFalse(parkingRecordDTO.getParking().getId())
                .orElseThrow(() -> new NotFoundException(String.format("The parking with ID %d does not exist.", parkingRecordDTO.getParking().getId())));
        Vehicle vehicle = vehicleRepository.findVehicleByIdAndDeletedFalse(parkingRecordDTO.getVehicle().getId())
                .orElseThrow(() -> new NotFoundException(String.format("The vehicle with ID %d does not exist.", parkingRecordDTO.getVehicle().getId())));

        int totalVehicleRegistered = getQuantityVehicleByParkingIdAndVehicleType(parking.getId(), vehicle.getType());
        int maxVacancies = vehicle.getType().equals(VehicleType.CAR)
                ? parking.getCarVacancies()
                : parking.getMotorcycleVacancies();
        if (totalVehicleRegistered >= maxVacancies) {
            throw new BadRequestException("There are no vacancies");
        }

        if(parkingRecordRepository.vehicleIsInTheParking(parking.getId(), vehicle.getId()))
            throw new BadRequestException("Vehicle is already in the parking");

        parkingRecordDTO.setParking(parkingMapper.toDTO(parking));
        parkingRecordDTO.setVehicle(vehicleMapper.toDTO(vehicle));
        parkingRecordDTO.setEntryAt(LocalDateTime.now());
        parkingRecordDTO.setStatus(ParkingStatus.IN_PROGRESS);

        ParkingRecord parkingRecord = parkingRecordMapper.toEntity(parkingRecordDTO);
        return parkingRecordMapper.toDTO(parkingRecordRepository.save(parkingRecord));
    }

    public ParkingRecordDTO cancelRegister(Long id) {
        ParkingRecord parkingRecord = parkingRecordRepository.findParkingRecordByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException(String.format("The Parking Record with ID %d does not exist", id)));
        if(!parkingRecord.getStatus().equals(ParkingStatus.IN_PROGRESS)) {
            throw new BadRequestException("Can not cancel the Parking Record with Status: " + parkingRecord.getStatus());
        }
        parkingRecord.setStatus(ParkingStatus.CANCELLED);
        parkingRecord = parkingRecordRepository.save(parkingRecord);
        return parkingRecordMapper.toDTO(parkingRecord);
    }

    public ParkingRecordDTO registerVehicleExit(Long id) {
        ParkingRecord parkingRecord = parkingRecordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("The Parking Record with ID %d does not exist", id)));
        if(parkingRecord.getStatus().equals(ParkingStatus.CANCELLED)) throw new BadRequestException("This register cannot be finished");
        parkingRecord.setStatus(ParkingStatus.FINISHED);
        parkingRecord.setExitAt(LocalDateTime.now());
        parkingRecord = parkingRecordRepository.save(parkingRecord);
        return parkingRecordMapper.toDTO(parkingRecord);
    }

    public void deleteParkingRecord(Long id) {
        ParkingRecord parkingRecord = parkingRecordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("The parking record with ID %d does not exist.", id)));
        parkingRecord.setDeleted(true);
        parkingRecordRepository.save(parkingRecord);
    }

    public void validateParkingRecord(ParkingRecordDTO parkingRecordDTO) {
        if(parkingRecordDTO.getVehicle() == null || parkingRecordDTO.getVehicle().getId() == null) {
            throw new BadRequestException("The vehicle field cannot be null");
        } else if(parkingRecordDTO.getParking() == null || parkingRecordDTO.getParking().getId() == null) {
            throw new BadRequestException("The parking field cannot be null");
        }
    }

    private Integer getQuantityVehicleByParkingIdAndVehicleType(Long parkingId, VehicleType vehicleType) {
        return parkingRecordRepository.countActiveVehiclesByType(parkingId, vehicleType);
    }
}
