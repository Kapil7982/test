package com.employeeManagement.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeeManagement.dto.CurrencyDTO;
import com.employeeManagement.dto.EmployeeDTO;
import com.employeeManagement.dto.EmployeeRequest;
import com.employeeManagement.dto.EmployeeResponseDTO;
import com.employeeManagement.dto.ResponseDTO;
import com.employeeManagement.models.Employee;
import com.employeeManagement.services.EmployeeService;

@RestController
@RequestMapping("/tci")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee-bonus")
    public ResponseEntity<String> saveEmployees(@RequestBody EmployeeRequest employeeRequest) {
        List<EmployeeDTO> employees = employeeRequest.getEmployees();
        employeeService.saveEmployees(employees);
        return ResponseEntity.ok("Employees saved successfully");
    }

    @GetMapping("/employee-bonus")
    public ResponseEntity<ResponseDTO> getEligibleEmployees(@RequestParam("date") String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date, formatter);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO("Invalid date format. Expected format: MMM-dd-yyyy"));
        }
        
        List<Employee> eligibleEmployees = employeeService.getEligibleEmployees(localDate);

        // Grouping by currency and mapping to EmployeeResponseDTO
        Map<String, List<EmployeeResponseDTO>> groupedByCurrency = eligibleEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getCurrency,
                        Collectors.mapping(emp -> new EmployeeResponseDTO(emp.getEmpName(), emp.getAmount()),
                                Collectors.toList())));

        // Sorting employees within each currency group
        Map<String, List<EmployeeResponseDTO>> sortedGroupedByCurrency = groupedByCurrency.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .sorted(Comparator.comparing(EmployeeResponseDTO::getEmpName))
                                .collect(Collectors.toList())
                ));

        // Creating a list of CurrencyDTO objects
        List<CurrencyDTO> currencyDTOs = sortedGroupedByCurrency.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> new CurrencyDTO(entry.getKey(), removeDuplicates(entry.getValue())))
                .collect(Collectors.toList());

        // Preparing the final response
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setErrorMessage("");
        responseDTO.setData(currencyDTOs);

        return ResponseEntity.ok(responseDTO);
    }
    
    // Helper method to remove duplicate EmployeeResponseDTOs based on empName
    private List<EmployeeResponseDTO> removeDuplicates(List<EmployeeResponseDTO> employees) {
        Map<String, EmployeeResponseDTO> employeeMap = new LinkedHashMap<>();

        for (EmployeeResponseDTO employee : employees) {
            employeeMap.putIfAbsent(employee.getEmpName(), employee);
        }

        return new ArrayList<>(employeeMap.values());
    }

}
