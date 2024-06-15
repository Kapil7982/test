package com.employeeManagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDTO {
    
	private String empName;
    private String department;
    private Integer amount;
    private String currency;
    private String joiningDate;
    private String exitDate;
    
    
	public EmployeeDTO(String empName, String department, Integer amount, String currency, String joiningDate,
			String exitDate) {
		this.empName = empName;
		this.department = department;
		this.amount = amount;
		this.currency = currency;
		this.joiningDate = joiningDate;
		this.exitDate = exitDate;
	}

    
    
}
