package com.parkingmanager.api.service;

import com.parkingmanager.api.dto.VehicleDTO;
import com.parkingmanager.api.exception.VehicleNotFoundException;
import com.parkingmanager.api.exception.VehicleNotSavedException;
import com.parkingmanager.api.mapper.VehicleMapper;
import com.parkingmanager.api.model.Vehicle;
import com.parkingmanager.api.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        try {
            Vehicle vehicleEntity = vehicleMapper.toEntity(vehicleDTO);
            Vehicle vehicleSaved = vehicleRepository.save(vehicleEntity);
            return vehicleMapper.toDTO(vehicleSaved);
        } catch (Exception e) {
            throw new VehicleNotSavedException(e);
        }
    }

    public VehicleDTO listVehicleById(Long id) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + id));
        return vehicleMapper.toDTO(existingVehicle);
    }

    public List<VehicleDTO> listVehicle() {
        return vehicleMapper.toListVehicleDTO(vehicleRepository.findAll());
    }

    public VehicleDTO updateVehicleById(Long id, VehicleDTO vehicleDTO) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + id));

        existingVehicle.setBrand(vehicleDTO.getBrand());
        existingVehicle.setColor(vehicleDTO.getColor());
        existingVehicle.setNumberPlate(vehicleDTO.getNumberPlate());
        existingVehicle.setModel(vehicleDTO.getModel());
        existingVehicle.setType(vehicleDTO.getType());

        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
        return vehicleMapper.toDTO(updatedVehicle);
    }

    public void deleteVehicleById(Long id) {
        vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + id));
        vehicleRepository.deleteById(id);
    }

}
