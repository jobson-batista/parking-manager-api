package com.parkingmanager.api.controller;

import com.parkingmanager.api.dto.ParkingDTO;
import com.parkingmanager.api.model.Stats;
import com.parkingmanager.api.service.ParkingService;
import com.parkingmanager.api.service.ReportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Tag(name = "Parking", description = "Operations related to parking management")
public class ParkingController {

    private final ParkingService parkingService;
    private final ReportService reportService;

    ParkingController(
            ParkingService parkingService,
            ReportService reportService
    ) {
        this.parkingService = parkingService;
        this.reportService = reportService;
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

    @GetMapping("/report/{id}/pdf")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) throws JRException {
        byte[] pdf = reportService.generatePdfReport(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=parking-report.pdf")
                .body(pdf);
    }
}
