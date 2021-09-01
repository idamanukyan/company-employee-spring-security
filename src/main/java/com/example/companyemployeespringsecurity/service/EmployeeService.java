package com.example.companyemployeespringsecurity.service;

import com.example.companyemployeespringsecurity.model.Employee;
import com.example.companyemployeespringsecurity.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void deleteByCompanyId(int id) {
        employeeRepository.deleteAllByCompanyId(id);
    }

    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> findEmployeeByCompanyId(int id) {
        return employeeRepository.findEmployeeByCompanyId(id);
    }

    public Optional<Employee> findEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

}
