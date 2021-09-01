package com.example.companyemployeespringsecurity;

import com.example.companyemployeespringsecurity.model.Company;
import com.example.companyemployeespringsecurity.model.Employee;
import com.example.companyemployeespringsecurity.model.EmployeeType;
import com.example.companyemployeespringsecurity.repository.CompanyRepository;
import com.example.companyemployeespringsecurity.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CompanyEmployeeSpringSecurityApplication implements CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(CompanyEmployeeSpringSecurityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (!employeeRepository.findByEmail("admin").isPresent()) {

            Company company = companyRepository.save(Company.builder()
                    .name("Default Company")
                    .address("Default Address")
                    .build());

            employeeRepository.save(Employee.builder()
                    .email("admin")
                    .phoneNumber("9999")
                    .surname("admin")
                    .name("admin")
                    .password(passwordEncoder.encode("admin"))
                    .salary(1.00)
                    .employeeType(EmployeeType.COMPANY_ADMIN)
                    .company(company)
                    .build());
        }
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
