package com.example.companyemployeespringsecurity.controller;

import com.example.companyemployeespringsecurity.model.Company;
import com.example.companyemployeespringsecurity.model.Employee;
import com.example.companyemployeespringsecurity.model.EmployeeType;
import com.example.companyemployeespringsecurity.security.CurrentUser;
import com.example.companyemployeespringsecurity.service.CompanyService;
import com.example.companyemployeespringsecurity.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final EmployeeService employeeService;
    private final CompanyService companyService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/admin")
    public String admin(ModelMap modelMap) {
        EmployeeType[] employeeType = EmployeeType.values();
        modelMap.addAttribute("companies", companyService.findAll());
        modelMap.addAttribute("employees", employeeService.findAll());
        modelMap.addAttribute("employeeType", employeeType);
        return "admin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginSucceed")
    public String loginSucceed(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser.getEmployee().getEmployeeType() == EmployeeType.COMPANY_ADMIN) {
            return "redirect:/admin";
        } else if (currentUser.getEmployee().getEmployeeType() == EmployeeType.COMPANY_USER) {
            return "redirect:/employees";
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register(ModelMap modelMap) {
        List<Company> companies = companyService.findAll();
        EmployeeType[] employeeType = EmployeeType.values();
        modelMap.addAttribute("companies", companies);
        modelMap.addAttribute("employeeType", employeeType);
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setEmployeeType(EmployeeType.COMPANY_USER);
        employeeService.save(employee);
        return "redirect:/login";
    }
}
