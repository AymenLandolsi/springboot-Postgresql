package com.scalyz.scalyz.controller;

import com.scalyz.scalyz.service.DatabaseHealthService;
import com.scalyz.scalyz.service.ServiceIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {
    private final DatabaseHealthService dbHealth;
    private final ServiceIdService serviceIdService;

    public HealthController(DatabaseHealthService dbHealth, ServiceIdService serviceIdService) {
        this.dbHealth = dbHealth;
        this.serviceIdService = serviceIdService;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        boolean dbUp = dbHealth.isUp();
        return ResponseEntity.ok(Map.of(
                "status", dbUp ? "UP" : "DOWN",
                "db", dbUp ? "UP" : "DOWN",
                "serviceId", serviceIdService.getServiceId()
        ));
    }
}
