package com.parkingmanager.api.controller;

import com.parkingmanager.api.dto.ParkingDTO;
import com.parkingmanager.api.model.Stats;
import com.parkingmanager.api.service.ParkingService;
import jakarta.servlet.ServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ParkingDTO>> findAllParking() {
        return ResponseEntity.ok(parkingService.findAllParking());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findParkingById(@PathVariable Long id) {
        return ResponseEntity.ok(parkingService.findParkingById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> updateParkingById(@PathVariable Long id, @RequestBody ParkingDTO parkingDTO) {
        return ResponseEntity.ok(parkingService.updateParkingById(id, parkingDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingById(@PathVariable Long id) {
        parkingService.deleteParkingById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/stats")
    public ResponseEntity<Stats> getStatsParkingById(@PathVariable Long id) {
        return ResponseEntity.ok(parkingService.getStatsByParking(id));
    }
}
