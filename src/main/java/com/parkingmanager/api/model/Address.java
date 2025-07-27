package com.parkingmanager.api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String number;
    private String city;
    private String state;
    private String zipCode;
}
