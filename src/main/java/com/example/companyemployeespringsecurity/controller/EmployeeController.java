package com.example.companyemployeespringsecurity.controller;

import com.example.companyemployeespringsecurity.model.Company;
import com.example.companyemployeespringsecurity.model.Employee;
import com.example.companyemployeespringsecurity.security.CurrentUser;
import com.example.companyemployeespringsecurity.service.CompanyService;
import com.example.companyemployeespringsecurity.service.EmployeeService;
import com.example.companyemployeespringsecurity.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final CompanyService companyService;
    private final TopicService topicService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/employees")
    public String getEmployees(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Employee> employees = employeeService.findEmployeeByCompanyId(currentUser.getEmployee().getCompany().getId());
        modelMap.addAttribute("employees", employees);
        modelMap.addAttribute("topics", topicService.findALlByEmployeeId(currentUser.getEmployee().getCompany().getId()));
        modelMap.addAttribute("user", currentUser);
        return "employees";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(ModelMap modelMap) {
        List<Company> all = companyService.findAll();
        modelMap.addAttribute("companies", all);
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeService.save(employee);
        Company company = companyService.getById(employee.getCompany().getId());
        company.setSize(company.getSize() + 1);
        companyService.save(company);
        return "redirect:/employees";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }
}
