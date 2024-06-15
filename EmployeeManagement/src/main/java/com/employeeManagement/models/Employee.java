package com.employeeManagement.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
public class Employee {
	
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String empName;
 private Integer amount;
 private String currency;
 private LocalDate joiningDate;
 private LocalDate exitDate;
 
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "department_id")
 private Department department;

 public Employee(String empName, Department department, Integer amount, String currency, LocalDate joiningDate, LocalDate exitDate) {
     this.empName = empName;
     this.department = department;
     this.amount = amount;
     this.currency = currency;
     this.joiningDate = joiningDate;
     this.exitDate = exitDate;
 }
}



