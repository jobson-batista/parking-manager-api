package com.parkingmanager.api.exception;

public class VehicleNotFoundException extends RuntimeException {

    private String message = "Vehicle not found";
    private String description = "Vehicle not found";

    public VehicleNotFoundException(String description) {
        this.description = description;
    }
}
