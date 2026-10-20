package com.acme.employeehub.ui.controller;

import com.acme.employeehub.ui.config.ApiMetrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping({"/api/metrics", "/api/actuator"})
public class MetricsController {

    @GetMapping(value = {"", "/custom-metrics", "/custom"})
    public Map<String, Object> getCustomMetrics() {
        return Map.of(
                "employee-controller.count", ApiMetrics.getCount("EmployeeController.getEmployees"),
                "employee-controller.avg-ms", ApiMetrics.getAverageTimeMs("EmployeeController.getEmployees"),
                "highest-paid.count", ApiMetrics.getCount("EmployeeController.getHighestPaidEmployee"),
                "highest-paid.avg-ms", ApiMetrics.getAverageTimeMs("EmployeeController.getHighestPaidEmployee"),
                "country-counts.count", ApiMetrics.getCount("EmployeeController.getCountryCounts"),
                "country-counts.avg-ms", ApiMetrics.getAverageTimeMs("EmployeeController.getCountryCounts")
        );
    }

    @GetMapping("/summary")
    public Map<String, Object> getSummaryMetrics() {
        return Map.of(
                "basePath", "/api/actuator",
                "customMetricsPath", "/api/metrics",
                "available", new String[]{"health", "metrics", "prometheus", "custom-metrics"}
        );
    }
}
