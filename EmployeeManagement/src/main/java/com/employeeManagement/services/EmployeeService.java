
package com.employeeManagement.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeManagement.dto.EmployeeDTO;
import com.employeeManagement.models.Department;
import com.employeeManagement.models.Employee;
import com.employeeManagement.repositories.DepartmentRepository;
import com.employeeManagement.repositories.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Transactional
    public void saveEmployees(List<EmployeeDTO> employeeDTOs) {
    	logger.info("Saving employees");
        for (EmployeeDTO dto : employeeDTOs) {
        	try {
                logger.info("Processing employee: {}", dto.getEmpName());
            Department department = departmentRepository.findByName(dto.getDepartment())
                .orElseGet(() -> {
                    Department newDept = new Department(dto.getDepartment());
                    logger.info("Creating new department: {}", dto.getDepartment());
                    return departmentRepository.save(newDept);
                });

            Employee employee = new Employee();
            employee.setEmpName(dto.getEmpName());
            employee.setDepartment(department);
            employee.setAmount(dto.getAmount());
            employee.setCurrency(dto.getCurrency());
            employee.setJoiningDate(LocalDate.parse(dto.getJoiningDate(), DateTimeFormatter.ofPattern("MMM-dd-yyyy")));
            employee.setExitDate(LocalDate.parse(dto.getExitDate(), DateTimeFormatter.ofPattern("MMM-dd-yyyy")));

            employeeRepository.save(employee);	
            logger.info("Saved employee: {}", dto.getEmpName());
        	} catch (Exception e) {
                logger.error("Error saving employee: {}", dto.getEmpName(), e);
                throw e;
            }
        }
    }

    public List<Employee> getEligibleEmployees(LocalDate date) {
        return employeeRepository.findAll().stream()
            .filter(employee -> !employee.getJoiningDate().isAfter(date) && !employee.getExitDate().isBefore(date))
            .distinct()
            .collect(Collectors.toList());
    }
}

