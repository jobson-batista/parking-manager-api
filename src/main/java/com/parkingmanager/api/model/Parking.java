package com.parkingmanager.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private Integer motorcycleVacancies;

    @Column(nullable = false)
    private Integer carVacancies;

    @Embedded
    private Address address;

    private boolean deleted = false;

}
