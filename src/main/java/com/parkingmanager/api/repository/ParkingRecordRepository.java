package com.parkingmanager.api.repository;

import com.parkingmanager.api.enums.VehicleType;
import com.parkingmanager.api.model.ParkingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRecordRepository extends JpaRepository<ParkingRecord, Long> {

    List<ParkingRecord> findParkingRecordsByDeletedFalse();
    Optional<ParkingRecord> findParkingRecordByIdAndDeletedFalse(Long id);
    List<ParkingRecord> findAllByDeletedFalseAndParking_Id(Long id);

    @Query("SELECT COUNT(pr) FROM ParkingRecord pr " +
            "WHERE pr.parking.id = :parkingId " +
            "AND pr.exitAt IS NULL " +
            "AND pr.vehicle.type = :vehicleType " +
            "AND pr.deleted = false")
    Integer countActiveVehiclesByType(@Param("parkingId") Long parkingId,
                                      @Param("vehicleType") VehicleType vehicleType);

    @Query("SELECT EXISTS (" +
            "SELECT 1 FROM ParkingRecord pr " +
            "WHERE pr.parking.id = :parkingId " +
            "AND pr.exitAt IS NULL " +
            "AND pr.vehicle.id = :vehicleId " +
            "AND pr.deleted = false)")
    boolean vehicleIsInTheParking(@Param("parkingId") Long parkingId,
                                  @Param("vehicleId") Long vehicleId);
}
