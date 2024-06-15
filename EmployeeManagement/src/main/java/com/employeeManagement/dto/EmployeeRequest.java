package com.employeeManagement.dto;


import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeRequest {

	private List<EmployeeDTO> employees;

	public EmployeeRequest(List<EmployeeDTO> employees) {
		super();
		this.employees = employees;
	}
	
	
}
