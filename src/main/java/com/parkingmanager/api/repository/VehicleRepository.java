package com.parkingmanager.api.repository;

import com.parkingmanager.api.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findVehicleByNumberPlateAndDeletedFalse(String numberPlate);
    Optional<Vehicle> findVehicleByIdAndDeletedFalse(Long id);
    List<Vehicle> findVehiclesByDeletedFalse();
}
