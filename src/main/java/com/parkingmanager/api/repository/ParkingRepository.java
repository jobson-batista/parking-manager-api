package com.parkingmanager.api.repository;

import com.parkingmanager.api.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    List<Parking> findByDeletedFalse();

    Optional<Parking> findByIdAndDeletedFalse(Long id);

    Optional<Parking> findParkingByNameAndCnpjAndDeletedFalse(String name, String cnpj);

}
