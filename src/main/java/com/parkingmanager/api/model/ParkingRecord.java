package com.parkingmanager.api.model;

import com.parkingmanager.api.enums.ParkingStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ParkingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "parking_id")
    private Parking parking;

    private LocalDateTime entryAt;

    private LocalDateTime exitAt;

    @Enumerated(EnumType.STRING)
    private ParkingStatus status;
}
