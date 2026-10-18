package com.acme.employeehub.ui.dto;

public record EmployeeDto(
        String empId,
        String name,
        String email,
        String department,
        String location,
        Integer salary
) {
}
