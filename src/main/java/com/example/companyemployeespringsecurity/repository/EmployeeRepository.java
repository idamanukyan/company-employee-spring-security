package com.example.companyemployeespringsecurity.repository;

import com.example.companyemployeespringsecurity.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmail(String email);

    List<Employee> findEmployeeByCompanyId(int id);

    void deleteById(int id);

    void deleteAllByCompanyId(int id);


}
