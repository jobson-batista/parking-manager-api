package com.parkingmanager.api.controller;

import com.parkingmanager.api.dto.VehicleDTO;
import com.parkingmanager.api.service.VehicleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@Tag(name = "Vehicle", description = "Operations related to vehicle registration and management")
public class VehicleController {

    private final VehicleService vehicleService;

    VehicleController(
            VehicleService vehicleService
    ) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO vehicle = vehicleService.saveVehicle(vehicleDTO);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> listVehicle() {
        return ResponseEntity.ok(vehicleService.listVehicle());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> listVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.listVehicleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicleById(@PathVariable Long id, @RequestBody VehicleDTO dto) {
        return ResponseEntity.ok(vehicleService.updateVehicleById(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleById(@PathVariable Long id) {
        vehicleService.deleteVehicleById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
