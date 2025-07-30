package com.parkingmanager.api.repository;

import com.parkingmanager.api.model.ParkingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRecordRepository extends JpaRepository<ParkingRecord, Long> {

    List<ParkingRecord> findParkingRecordsByDeletedFalse();
    Optional<ParkingRecord> findParkingRecordByIdAndDeletedFalse(Long id);
    List<ParkingRecord> findAllByDeletedFalseAndParking_Id(Long id);
}
