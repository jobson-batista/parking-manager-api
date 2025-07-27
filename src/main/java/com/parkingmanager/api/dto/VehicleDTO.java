package com.parkingmanager.api.dto;

import com.parkingmanager.api.enums.VehicleType;
import com.parkingmanager.api.model.ParkingRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDTO {

    private Long id;

    private String brand;

    private String model;

    private String color;

    private String numberPlate;

    private VehicleType type;

}
