package com.employeeManagement.dto;

import java.util.List;

import lombok.Data;

@Data
public class CurrencyDTO {
   
	private String currency;
    private List<EmployeeResponseDTO> employees;
    
    public CurrencyDTO(String currency, List<EmployeeResponseDTO> employees) {
        this.currency = currency;
        this.employees = employees;
    }
}