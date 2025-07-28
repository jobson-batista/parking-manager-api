package com.parkingmanager.api.dto;

import com.parkingmanager.api.model.Address;
import com.parkingmanager.api.model.ParkingRecord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParkingDTO {

    private Long id;

    private String name;

    private String cnpj;

    private String phone;

    private Integer motorcycleVacancies;

    private Integer carVacancies;

    private List<ParkingRecord> records = new ArrayList<>();

    private Address address;
}
