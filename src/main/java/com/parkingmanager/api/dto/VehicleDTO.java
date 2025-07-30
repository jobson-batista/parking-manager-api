package com.parkingmanager.api.dto;

import com.parkingmanager.api.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
