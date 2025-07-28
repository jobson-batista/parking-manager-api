package com.parkingmanager.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingNotFound extends RuntimeException {

    private String message = "Parking not found";
    private String description = "Parking not found with that ID.";

    public ParkingNotFound(String description) {
        this.description = description;
    }

    public ParkingNotFound(Long id) {
        this.description = "Parking not found with this ID: "+id;
    }

}
