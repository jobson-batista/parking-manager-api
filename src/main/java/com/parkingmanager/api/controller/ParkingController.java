package com.parkingmanager.api.controller;

import com.parkingmanager.api.dto.ParkingDTO;
import com.parkingmanager.api.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;

    ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> saveParking(@RequestBody ParkingDTO parkingDTO) {
        return ResponseEntity.ok(parkingService.saveParking(parkingDTO));
    }
}
