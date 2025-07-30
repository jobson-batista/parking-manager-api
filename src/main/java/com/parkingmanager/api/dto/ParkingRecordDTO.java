package com.parkingmanager.api.dto;

import com.parkingmanager.api.enums.ParkingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingRecordDTO {

    private Long id;

    private VehicleDTO vehicle;

    private ParkingDTO parking;

    private LocalDateTime entryAt;

    private LocalDateTime exitAt;

    private ParkingStatus status;

}

