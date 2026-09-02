package com.stockflow.controller;

import com.stockflow.dto.LowStockAlertDto;
import com.stockflow.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class AlertController {
    
    private final AlertService alertService;
    
    @GetMapping("/{companyId}/alerts/low-stock")
    public ResponseEntity<List<LowStockAlertDto>> getLowStockAlerts(@PathVariable Long companyId) {
        List<LowStockAlertDto> alerts = alertService.getLowStockAlerts(companyId);
        return ResponseEntity.ok(alerts);
    }
}
