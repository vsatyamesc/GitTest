package com.satyam.hrportal.controller;

import com.satyam.hrportal.model.Employee;
import com.satyam.hrportal.service.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private HRService hrPortalService;

    // List employees
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> listEmployees() {
        List<Employee> employees = hrPortalService.getAllEmployee();
        return ResponseEntity.ok(employees);
    }
    @PostMapping("/employees/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee emp1 = hrPortalService.saveEmployee(employee);
        return ResponseEntity.ok(emp1);
    }

    // Update employee details
    @PostMapping("/employees/edit/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        Employee emp = hrPortalService.saveEmployee(employee);
        return ResponseEntity.ok(emp);
    }

    // Delete employee
    @DeleteMapping("/employees/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        hrPortalService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
