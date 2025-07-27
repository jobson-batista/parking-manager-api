package com.parkingmanager.api.model;

import com.parkingmanager.api.enums.VehicleType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false, unique = true)
    private String numberPlate;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private VehicleType type;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingRecord> records = new ArrayList<>();
}
