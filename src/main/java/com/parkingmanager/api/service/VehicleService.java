package com.parkingmanager.api.service;

import com.parkingmanager.api.dto.VehicleDTO;
import com.parkingmanager.api.exception.BadRequestException;
import com.parkingmanager.api.exception.NotFoundException;
import com.parkingmanager.api.mapper.VehicleMapper;
import com.parkingmanager.api.model.Vehicle;
import com.parkingmanager.api.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    VehicleService(
            VehicleRepository repository,
            VehicleMapper vehicleMapper
    ) {
        this.vehicleRepository = repository;
        this.vehicleMapper = vehicleMapper;
    }

    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicleEntity = vehicleMapper.toEntity(vehicleDTO);
        Optional<Vehicle> existingVehicle = vehicleRepository.findVehicleByNumberPlateAndDeletedFalse(vehicleDTO.getNumberPlate());
        if (existingVehicle.isPresent()) throw new BadRequestException("There is already a vehicle with this plate number");
        Vehicle vehicleSaved = vehicleRepository.save(vehicleEntity);
        return vehicleMapper.toDTO(vehicleSaved);
    }

    public VehicleDTO listVehicleById(Long id) {
        Vehicle existingVehicle = vehicleRepository.findVehicleByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found with ID: " + id));
        return vehicleMapper.toDTO(existingVehicle);
    }

    public List<VehicleDTO> listVehicle() {
        return vehicleMapper.toListVehicleDTO(vehicleRepository.findVehiclesByDeletedFalse());
    }

    public VehicleDTO updateVehicleById(Long id, VehicleDTO vehicleDTO) {
        Vehicle existingVehicle = vehicleRepository.findVehicleByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found with ID: " + id));

        existingVehicle.setBrand(vehicleDTO.getBrand());
        existingVehicle.setColor(vehicleDTO.getColor());
        existingVehicle.setNumberPlate(vehicleDTO.getNumberPlate());
        existingVehicle.setModel(vehicleDTO.getModel());
        existingVehicle.setType(vehicleDTO.getType());

        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
        return vehicleMapper.toDTO(updatedVehicle);
    }

    public void deleteVehicleById(Long id) {
        vehicleRepository.findVehicleByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found with ID: " + id));
        vehicleRepository.deleteById(id);
    }

}
