package com.parkingmanager.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleNotSavedException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(VehicleNotSavedException.class);

    private String message = "Vehicle not saved";
    private String description = "It was not possible to save the vehicle. Check the request body.";

    public VehicleNotSavedException(Exception e) {
        e.printStackTrace();
    }
}
