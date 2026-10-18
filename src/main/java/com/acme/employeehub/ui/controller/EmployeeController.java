package com.acme.employeehub.ui.controller;

import com.acme.employeehub.ui.client.EmployeeRestClient;
import com.acme.employeehub.ui.dto.CountryEmployeeCountDto;
import com.acme.employeehub.ui.dto.EmployeeDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:4200"})
public class EmployeeController {

    private final EmployeeRestClient employeeRestClient;

    public EmployeeController(EmployeeRestClient employeeRestClient) {
        this.employeeRestClient = employeeRestClient;
    }

    @GetMapping("/employees")
    public List<EmployeeDto> getEmployees(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return employeeRestClient.getAllEmployees(pageNumber, pageSize);
    }

    @GetMapping("/employees/highest-paid")
    public List<EmployeeDto> getHighestPaidEmployee() {
        return employeeRestClient.getHighestPaidEmployee();
    }

    @GetMapping("/employees/country-counts")
    public List<CountryEmployeeCountDto> getCountryCounts() {
        return employeeRestClient.getCountryCounts();
    }
}
