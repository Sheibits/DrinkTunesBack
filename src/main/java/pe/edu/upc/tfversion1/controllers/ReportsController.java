package pe.edu.upc.tfversion1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.tfversion1.dtos.ReportDataDTO;
import pe.edu.upc.tfversion1.serviceinterfaces.IReportService;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private IReportService reportService;

    // Reporte exclusivo para Admin: Distribución de roles
    @GetMapping("/role-distribution")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ReportDataDTO>> getRoleDistribution() {
        return ResponseEntity.ok(reportService.getRoleDistribution());
    }

    // Reporte exclusivo para Admin: Resumen de actividades
    @GetMapping("/activity-summary")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ReportDataDTO>> getActivitySummary() {
        return ResponseEntity.ok(reportService.getActivitySummary());
    }

    // Reporte para todos: Mensajes por usuario
    @GetMapping("/messages-by-user")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')")
    public ResponseEntity<List<ReportDataDTO>> getMessagesByUser() {
        return ResponseEntity.ok(reportService.getMessagesByUser());
    }

    // Reporte para todos: Anuncios por restaurante
    @GetMapping("/ads-by-restaurant")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RESTAURANTE', 'ARTISTA')")
    public ResponseEntity<List<ReportDataDTO>> getAdsByRestaurant() {
        return ResponseEntity.ok(reportService.getAdsByRestaurant());
    }
}
