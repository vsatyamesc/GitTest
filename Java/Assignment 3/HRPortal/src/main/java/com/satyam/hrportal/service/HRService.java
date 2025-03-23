package com.satyam.hrportal.service;

import com.satyam.hrportal.model.Employee;
import com.satyam.hrportal.model.HR;
import com.satyam.hrportal.repository.EmployeeRepository;
import com.satyam.hrportal.repository.HRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HRService {
    @Autowired
    private HRRepository hrRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean hrAuth(String email, String password){
        if (email == null || password == null){
            return false;
        }
        HR hr = hrRepository.findByEmail(email);
        return hr != null && hr.getPassword().equals(password);
    }

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }
    public Optional<Employee> getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }
}
