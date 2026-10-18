package com.acme.employeehub.ui.model;

public record Employee (
        String empId,
        String name,
        String email,
        String department,
        Integer salary,
        String currency,
        String location
) { }