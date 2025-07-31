package com.parkingmanager.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stats {

    private String nameParking;
    private Integer totalEntryQuantity;
    private Integer totalExitQuantity;;
    private Integer entryQuantityPerHour;
    private Integer exitQuantityPerHour;
}
