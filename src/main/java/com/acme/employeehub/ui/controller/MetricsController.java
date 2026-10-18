package com.acme.employeehub.ui.controller;

import com.acme.employeehub.ui.config.ApiMetrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/actuator")
public class MetricsController {

    @GetMapping("/custom-metrics")
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
}
