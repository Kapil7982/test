package com.employeeManagement.serviceTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.employeeManagement.dto.EmployeeDTO;
import com.employeeManagement.models.Department;
import com.employeeManagement.models.Employee;
import com.employeeManagement.repositories.DepartmentRepository;
import com.employeeManagement.repositories.EmployeeRepository;
import com.employeeManagement.services.EmployeeService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@SpringBootTest
public class EmployeeServiceTests {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    public void testSaveEmployees() {
        
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        employeeDTOs.add(new EmployeeDTO("John Doe", "Marketing", 5000, "USD", "Jan-01-2024", "Dec-31-2024"));
        employeeDTOs.add(new EmployeeDTO("Jane Smith", "Sales", 6000, "EUR", "Feb-15-2024", "Dec-31-2024"));

        
        when(departmentRepository.findByName("Marketing")).thenReturn(Optional.of(new Department("Marketing")));
        when(departmentRepository.findByName("Sales")).thenReturn(Optional.of(new Department("Sales")));
        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> {
            Employee employee = invocation.getArgument(0);
            employee.setId(1L); // Simulate saving with an ID
            return employee;
        });

        
        employeeService.saveEmployees(employeeDTOs);

        verify(employeeRepository, times(employeeDTOs.size())).save(any(Employee.class));
    }


    @Test
    public void testGetEligibleEmployees() {
        
        LocalDate date = LocalDate.parse("2024-06-15", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee("John Doe", new Department("Marketing"), 5000, "USD", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31")));
        mockEmployees.add(new Employee("Jane Smith", new Department("Sales"), 6000, "EUR", LocalDate.parse("2024-02-15"), LocalDate.parse("2024-12-31")));

        
        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        
        List<Employee> eligibleEmployees = employeeService.getEligibleEmployees(date);

        
        assertEquals(mockEmployees.size(), eligibleEmployees.size());
        assertIterableEquals(mockEmployees, eligibleEmployees);
    }
}

