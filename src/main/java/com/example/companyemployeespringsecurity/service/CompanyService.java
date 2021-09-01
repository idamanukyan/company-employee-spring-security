package com.example.companyemployeespringsecurity.service;

import com.example.companyemployeespringsecurity.model.Company;
import com.example.companyemployeespringsecurity.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CompanyService {
    private final CompanyRepository companyRepository;

    public void save(Company company) {
        companyRepository.save(company);
    }

    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    public Company getById(int id) {
        return companyRepository.getById(id);
    }

    public void deleteById(int id) {
        companyRepository.deleteById(id);
    }

}
