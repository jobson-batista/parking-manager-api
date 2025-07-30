package com.parkingmanager.api.model;

import com.parkingmanager.api.enums.ParkingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Vehicle vehicle;

    @ManyToOne(optional = false)
    private Parking parking;

    private LocalDateTime entryAt;

    private LocalDateTime exitAt;

    @Enumerated(EnumType.STRING)
    private ParkingStatus status;

    private boolean deleted = false;
}
