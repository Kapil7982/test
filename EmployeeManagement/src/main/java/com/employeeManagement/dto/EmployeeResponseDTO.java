package com.employeeManagement.dto;


import lombok.Data;

@Data
public class EmployeeResponseDTO {
    private String empName;
    private Integer amount;

    public EmployeeResponseDTO(String empName, Integer amount) {
        this.empName = empName;
        this.amount = amount;
    }
}

