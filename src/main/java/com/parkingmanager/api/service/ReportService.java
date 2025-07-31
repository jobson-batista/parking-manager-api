package com.parkingmanager.api.service;

import com.parkingmanager.api.model.Stats;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    private final ParkingService parkingService;

    ReportService(
            ParkingService parkingService
    ) {
        this.parkingService = parkingService;
    }

    public byte[] generatePdfReport(Long parkingId) throws JRException {
        Stats stats = parkingService.getStatsByParking(parkingId);
        InputStream contentStream = getClass().getResourceAsStream("/reports/parking_report.jrxml");
        JasperReport content = JasperCompileManager.compileReport(contentStream);

        Map<String, Object> params = getStringObjectMap(stats);
        params.put("SUBREPORT_CONTENT", content);

        InputStream mainStream = getClass().getResourceAsStream("/reports/parking_report.jrxml");
        JasperReport mainReport = JasperCompileManager.compileReport(mainStream);
        JasperPrint print = JasperFillManager.fillReport(mainReport, params, new JREmptyDataSource());

        return JasperExportManager.exportReportToPdf(print);
    }

    private static Map<String, Object> getStringObjectMap(Stats stats) {
        Map<String, Object> params = new HashMap<>();
        params.put("subTitle", "Parking Usage Report");
        params.put("title", "Entry and Exit Statistics by Parking Location");
        params.put("parkingName", stats.getNameParking());
        params.put("totalEntry", stats.getTotalEntryQuantity());
        params.put("totalExit", stats.getTotalExitQuantity());
        params.put("entryPerHour", stats.getEntryQuantityPerHour());
        params.put("exitPerHour", stats.getExitQuantityPerHour());
        return params;
    }
}
