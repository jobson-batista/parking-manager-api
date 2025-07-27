package com.parkingmanager.api.model;

import jakarta.persistence.*;

@Entity
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Integer motocycleVacancies;

    @Column(nullable = false)
    private Integer carVacancies;
}
