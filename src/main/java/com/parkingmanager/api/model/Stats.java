package com.parkingmanager.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stats {

    private String nameParking;
    private Integer carVacanciesAvailable;
    private Integer motorVacanciesAvailable;
}
