package com.employeeManagement.controllerTest;

import com.employeeManagement.dto.EmployeeDTO;
import com.employeeManagement.dto.EmployeeRequest;
import com.employeeManagement.models.Department;
import com.employeeManagement.models.Employee;
import com.employeeManagement.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testSaveEmployees() throws Exception {
        
        List<EmployeeDTO> employeeDTOs = Arrays.asList(
                new EmployeeDTO("John Doe", "Marketing", 5000, "USD", "Jan-01-2024", "Dec-31-2024"),
                new EmployeeDTO("Jane Smith", "Sales", 6000, "EUR", "Feb-15-2024", "Dec-31-2024")
        );

        
        EmployeeRequest request = new EmployeeRequest();
        request.setEmployees(employeeDTOs);

       
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);

        
        mockMvc.perform(MockMvcRequestBuilders.post("/tci/employee-bonus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Employees saved successfully"));

        
        verify(employeeService).saveEmployees(employeeDTOs);
    }

    @Test
    public void testGetEligibleEmployees() throws Exception {
        
        LocalDate date = LocalDate.parse("2024-06-15", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Employee> mockEmployees = Arrays.asList(
                new Employee("John Doe", new Department("Marketing"), 5000, "USD", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31")),
                new Employee("Jane Smith", new Department("Sales"), 6000, "EUR", LocalDate.parse("2024-02-15"), LocalDate.parse("2024-12-31"))
        );

        
        when(employeeService.getEligibleEmployees(date)).thenReturn(mockEmployees);

        
        mockMvc.perform(MockMvcRequestBuilders.get("/tci/employee-bonus")
                .param("date", "Jun-15-2024"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMessage").isEmpty())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].currency").value("EUR"))
                .andExpect(jsonPath("$.data[0].employees[0].empName").value("Jane Smith"))
                .andExpect(jsonPath("$.data[0].employees[0].amount").value(6000))
                .andExpect(jsonPath("$.data[1].currency").value("USD"))
                .andExpect(jsonPath("$.data[1].employees[0].empName").value("John Doe"))
                .andExpect(jsonPath("$.data[1].employees[0].amount").value(5000));

        
        verify(employeeService).getEligibleEmployees(date);
    }

    
    
}

