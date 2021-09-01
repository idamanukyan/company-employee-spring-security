package com.example.companyemployeespringsecurity.repository;

import com.example.companyemployeespringsecurity.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
