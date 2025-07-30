package com.parkingmanager.api.controller;

import com.parkingmanager.api.dto.ParkingRecordDTO;
import com.parkingmanager.api.service.ParkingRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-record")
public class ParkingRecordController {

    private final ParkingRecordService parkingRecordService;

    public ParkingRecordController(
            ParkingRecordService parkingRecordService
    ) {
        this.parkingRecordService = parkingRecordService;
    }

    @GetMapping
    public ResponseEntity<List<ParkingRecordDTO>> findAllParkingRecord() {
        return ResponseEntity.ok(parkingRecordService.findAllParking());
    }

    @PostMapping
    public ResponseEntity<ParkingRecordDTO> registerEntryVehicle(@RequestBody ParkingRecordDTO parkingRecordDTO) {
        return ResponseEntity.ok(parkingRecordService.registerEntryVehicle(parkingRecordDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingRecordDTO> findParkingRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(parkingRecordService.findParkingById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingRecord(@PathVariable Long id) {
        parkingRecordService.deleteParkingRecord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<ParkingRecordDTO> cancelRegisterVehicle(@PathVariable Long id) {
        return ResponseEntity.ok(parkingRecordService.cancelRegister(id));
    }

    @PostMapping("/exit/{id}")
    public ResponseEntity<ParkingRecordDTO> registerVehicleExit(@PathVariable Long id) {
        return ResponseEntity.ok(parkingRecordService.registerVehicleExit(id));
    }

}
